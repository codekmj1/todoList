package com.teamsparta.kotlin.todolist.controller


import com.teamsparta.kotlin.common.auth.TokenInfo
import com.teamsparta.kotlin.common.dto.BaseResponse
import com.teamsparta.kotlin.todolist.dto.LoginRequest
import com.teamsparta.kotlin.todolist.dto.SignUpRequest
import com.teamsparta.kotlin.todolist.dto.UserResponse
import com.teamsparta.kotlin.todolist.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/users")
@RestController
class UserController (
    private val userService: UserService
){
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid signUpRequest: SignUpRequest): BaseResponse<UserResponse> {
        val userResponse: UserResponse = userService.signUp(signUpRequest)
        return BaseResponse(data = userResponse)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): BaseResponse<TokenInfo> {
        val tokenInfo = userService.login(loginRequest)
        return BaseResponse(data = tokenInfo)
    }



}