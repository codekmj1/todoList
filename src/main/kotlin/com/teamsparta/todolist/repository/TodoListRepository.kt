package com.teamsparta.todolist.repository

import com.teamsparta.todolist.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoListRepository : JpaRepository<Todo, Long> {
    fun findAllByOrderByCreatedDateDesc(): List<Todo>
}


