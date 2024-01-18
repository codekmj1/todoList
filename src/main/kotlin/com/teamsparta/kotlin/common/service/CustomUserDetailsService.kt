package com.teamsparta.kotlin.common.service

import com.teamsparta.kotlin.common.dto.CustomUser
import com.teamsparta.kotlin.user.entity.User
import com.teamsparta.kotlin.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails =
        userRepository.findByEmail(email)
            ?.let { createUserDetails(it) }
            ?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")
    private fun createUserDetails(user: User): UserDetails =
        CustomUser(
            user.email,
            user.id!!,
            user.nickname,
            passwordEncoder.encode(user.password),
            user.userRole!!.map { SimpleGrantedAuthority("ROLE_${it.role}") }
        )
}
