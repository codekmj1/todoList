package com.teamsparta.kotlin.todolist.service

import com.teamsparta.kotlin.common.auth.JwtTokenProvider
import com.teamsparta.kotlin.common.auth.TokenInfo
import com.teamsparta.kotlin.common.exception.InvalidInputException
import com.teamsparta.kotlin.todolist.dto.LoginRequest
import com.teamsparta.kotlin.todolist.dto.SignUpRequest
import com.teamsparta.kotlin.todolist.dto.UserResponse
import com.teamsparta.kotlin.todolist.entity.Users
import com.teamsparta.kotlin.todolist.entity.UserRole
import com.teamsparta.kotlin.todolist.entity.UserRoleEntity
import com.teamsparta.kotlin.todolist.repository.UsersRepository
import com.teamsparta.kotlin.todolist.repository.UsersRoleRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserServiceImpl(
    private val userRepository: UsersRepository,
    private val userRoleRepository: UsersRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
) : UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        var users: Users? = userRepository.findByEmail(signUpRequest.email)
        if (users != null) {
            throw InvalidInputException("email", "이미 등록된 email 입니다.")
        }

        users = Users(
            signUpRequest.email,
            createDelegatingPasswordEncoder().encode(signUpRequest.password),
            signUpRequest.nickname
        )

        val savedUser = userRepository.save(users)

        //권한 저장
        val userRole = UserRoleEntity(null, UserRole.MEMBER, users)
        userRoleRepository.save(userRole)

        return UserResponse(savedUser.id!!, savedUser.email, savedUser.nickname, savedUser.createdAt!!)
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