package com.teamsparta.kotlin.todolist.dto.commentsdto

import java.time.LocalDateTime

data class CommentsResponse(
    val id: Long?,
    val userId: Long,
    val todoId: Long?,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)