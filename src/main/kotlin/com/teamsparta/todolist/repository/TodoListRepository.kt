package com.teamsparta.todolist.repository

import com.teamsparta.todolist.entity.TodoList
import org.springframework.data.jpa.repository.JpaRepository

interface TodoListRepository : JpaRepository<TodoList, Long> {
    fun findAllByOrderByCreatedDateDesc(): List<TodoList>
}


