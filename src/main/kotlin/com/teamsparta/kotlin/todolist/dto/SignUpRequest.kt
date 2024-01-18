package com.teamsparta.kotlin.todolist.dto


data class SignUpRequest (
    var email: String,
    val password: String,
    val nickname: String
)