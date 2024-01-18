package com.teamsparta.kotlin.todolist.service

import com.teamsparta.kotlin.common.auth.TokenInfo
import com.teamsparta.kotlin.todolist.dto.LoginRequest
import com.teamsparta.kotlin.todolist.dto.SignUpRequest
import com.teamsparta.kotlin.todolist.dto.UserResponse

interface UserService {

    fun signUp(signUpRequest: SignUpRequest): UserResponse

    fun login(loginRequest: LoginRequest): TokenInfo


}