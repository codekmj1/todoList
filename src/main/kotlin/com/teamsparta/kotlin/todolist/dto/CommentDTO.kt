package com.teamsparta.kotlin.todolist.dto

import jakarta.validation.constraints.NotEmpty

data class CommentDTO(
    @field:NotEmpty(message = "Writer cannot be empty.")
    val writer: String,

    @field:NotEmpty(message = "Password cannot be empty.")
    val password: String,

    @field:NotEmpty(message = "Content cannot be empty.")
    val content: String
)

