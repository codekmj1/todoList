package com.teamsparta.kotlin.user.service

import com.teamsparta.kotlin.common.auth.JwtTokenProvider
import com.teamsparta.kotlin.common.auth.TokenInfo
import com.teamsparta.kotlin.common.exception.InvalidInputException
import com.teamsparta.kotlin.user.dto.LoginRequest
import com.teamsparta.kotlin.user.dto.SignUpRequest
import com.teamsparta.kotlin.user.entity.User
import com.teamsparta.kotlin.user.entity.UserRole
import com.teamsparta.kotlin.user.entity.UserRoleEntity
import com.teamsparta.kotlin.user.repository.UserRepository
import com.teamsparta.kotlin.user.repository.UserRoleRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userRoleRepository: UserRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
) : UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): String {
        var user: User? = userRepository.findByEmail(signUpRequest.email)
        if (user != null) {
            throw InvalidInputException("email", "이미 등록된 email 입니다.")
        }

        user = User(
            signUpRequest.email,
            createDelegatingPasswordEncoder().encode(signUpRequest.password),
            signUpRequest.nickname
        )

        userRepository.save(user)

        //권한 저장
        val userRole = UserRoleEntity(null, UserRole.MEMBER, user)
        userRoleRepository.save(userRole)

        return "회원가입 완료"
    }

    //로그인 토큰 발생
    @Transactional
    override fun login(loginRequest: LoginRequest): TokenInfo {
        val userInfo = userRepository.findByEmail(loginRequest.email)
            ?: throw InvalidInputException("email", "이메일(${loginRequest.email})이 존재하지 않습니다.")

        val isValidPassword = createDelegatingPasswordEncoder().matches(loginRequest.password, userInfo.password)
        if (!isValidPassword) {
            throw BadCredentialsException("잘못된 패스워드입니다.")
        }
        val authenticationToken = UsernamePasswordAuthenticationToken(userInfo.email, userInfo.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)

    }

}