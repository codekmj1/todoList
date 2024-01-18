package com.teamsparta.kotlin.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Password does not match")
class PasswordNotMatchException : RuntimeException()