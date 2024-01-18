package com.teamsparta.kotlin.user.controller


import com.teamsparta.kotlin.common.auth.TokenInfo
import com.teamsparta.kotlin.common.dto.BaseResponse
import com.teamsparta.kotlin.user.dto.LoginRequest
import com.teamsparta.kotlin.user.dto.SignUpRequest
import com.teamsparta.kotlin.user.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/users")
@RestController
class UserController (
    private val userService: UserService
){
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid signUpRequest: SignUpRequest): BaseResponse<Unit> {
        val resultMsg: String = userService.signUp(signUpRequest)
        return BaseResponse(message = resultMsg)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): BaseResponse<TokenInfo> {
        val tokenInfo = userService.login(loginRequest)
        return BaseResponse(data = tokenInfo)
    }



}