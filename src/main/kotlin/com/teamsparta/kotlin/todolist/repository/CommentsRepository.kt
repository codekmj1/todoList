package com.teamsparta.kotlin.todolist.repository

import com.teamsparta.kotlin.todolist.entity.Comments
import com.teamsparta.kotlin.todolist.entity.Todos
import org.springframework.data.jpa.repository.JpaRepository

interface CommentsRepository : JpaRepository<Comments, Long> {
    fun findByTodos(todos: Todos): List<Comments>

    override fun existsById(id: Long): Boolean
}