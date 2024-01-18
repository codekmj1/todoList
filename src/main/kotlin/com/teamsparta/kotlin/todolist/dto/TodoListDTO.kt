package com.teamsparta.kotlin.todolist.dto


import jakarta.validation.constraints.NotEmpty


data class TodoListDTO(
    @field:NotEmpty(message = "Title cannot be empty.")
    val title: String,

    @field:NotEmpty(message = "Content cannot be empty.")
    val content: String,

    @field:NotEmpty(message = "Writer cannot be empty.")
    val writer: String
)

