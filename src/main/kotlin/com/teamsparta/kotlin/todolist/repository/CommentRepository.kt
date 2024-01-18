package com.teamsparta.kotlin.todolist.repository

import com.teamsparta.kotlin.todolist.entity.Comment
import com.teamsparta.kotlin.todolist.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByTodo(todo: Todo): List<Comment>
    fun findByWriterAndPassword(writer: String, password: String): List<Comment>
    override fun existsById(id: Long): Boolean
}