package com.teamsparta.todolist.repository

import com.teamsparta.todolist.entity.Comment
import com.teamsparta.todolist.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByTodo(todo: Todo): List<Comment>
    fun findByWriterAndPassword(writer: String, password: String): List<Comment>
    override fun existsById(id: Long): Boolean
}