package com.teamsparta.kotlin.user.dto

data class LogoutRequest(
    val accessToken: String,
    val refreshToken: String
)
