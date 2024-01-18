package com.teamsparta.kotlin.common.auth

data class TokenInfo (
    val grantType: String,
    val accessToken: String,
)