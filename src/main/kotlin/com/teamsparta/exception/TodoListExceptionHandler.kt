package com.teamsparta.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.IllegalArgumentException

@ControllerAdvice
class TodoListExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): String {
        return exception.message.toString()
    }
    // 비밀번호 예외 처리
    @ExceptionHandler(PasswordNotMatchException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handlePasswordNotMatchException(exception: PasswordNotMatchException): String {
        return exception.message.toString()
    }
}


