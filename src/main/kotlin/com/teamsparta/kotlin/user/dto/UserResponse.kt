package com.teamsparta.kotlin.user.dto

import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val createdAt: LocalDateTime
)