package com.teamsparta.kotlin.user.repository


import com.teamsparta.kotlin.user.entity.User
import com.teamsparta.kotlin.user.entity.UserRoleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}

interface UserRoleRepository: JpaRepository<UserRoleEntity,Long>{
}