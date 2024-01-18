package com.teamsparta.kotlin.todolist.repository

import com.teamsparta.kotlin.todolist.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoListRepository : JpaRepository<Todo, Long> {
    fun findAllByOrderByCreatedDateDesc(): List<Todo>
}


