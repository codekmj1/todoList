package com.teamsparta.kotlin.user.service

import com.teamsparta.kotlin.common.auth.TokenInfo
import com.teamsparta.kotlin.user.dto.LoginRequest
import com.teamsparta.kotlin.user.dto.SignUpRequest

interface UserService {

    fun signUp(signUpRequest: SignUpRequest): String

    fun login(loginRequest: LoginRequest): TokenInfo


}