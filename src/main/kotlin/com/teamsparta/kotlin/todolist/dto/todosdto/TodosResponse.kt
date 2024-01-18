package com.teamsparta.kotlin.todolist.dto.todosdto

import java.time.LocalDateTime

data class TodosResponse(
    val id: Long?,  // null 허용
    val title: String,
    val content: String,
    val userId: Long?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)