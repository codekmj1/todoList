package com.teamsparta.kotlin.common.service

import com.teamsparta.kotlin.common.dto.CustomUser
import com.teamsparta.kotlin.todolist.entity.Users
import com.teamsparta.kotlin.todolist.repository.UsersRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails =
        userRepository.findByEmail(email)
            ?.let { createUserDetails(it) }
            ?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")
    private fun createUserDetails(users: Users): UserDetails =
        CustomUser(
            users.email,
            users.id!!,
            users.nickname,
            passwordEncoder.encode(users.password),
            users.userRole!!.map { SimpleGrantedAuthority("ROLE_${it.role}") }
        )
}
