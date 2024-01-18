package com.teamsparta.kotlin.todolist.repository


import com.teamsparta.kotlin.todolist.entity.Users
import com.teamsparta.kotlin.todolist.entity.UserRoleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<Users, Long> {
    fun findByEmail(email: String): Users?
}

interface UsersRoleRepository: JpaRepository<UserRoleEntity,Long>{
}