package com.teamsparta.todolist.service

import com.teamsparta.todolist.dto.TodoListDTO
import com.teamsparta.todolist.entity.Todo

interface TodoListService {
    fun createTodoList(todoListDTO: TodoListDTO): Todo
    fun getTodoList(id: Long): Todo
    fun getAllTodoList(): List<Todo>
    fun updateTodoList(id: Long, todoListDTO: TodoListDTO): Todo
    fun deleteTodoList(id: Long)
}


