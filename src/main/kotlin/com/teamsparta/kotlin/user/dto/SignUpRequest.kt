package com.teamsparta.kotlin.user.dto


data class SignUpRequest (
    var email: String,
    val password: String,
    val nickname: String
)