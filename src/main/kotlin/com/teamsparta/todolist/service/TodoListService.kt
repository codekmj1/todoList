package com.teamsparta.todolist.service

import com.teamsparta.todolist.dto.TodoListDTO
import com.teamsparta.todolist.entity.TodoList

interface TodoListService {
    fun createTodoList(todoListDTO: TodoListDTO): TodoList
    fun getTodoList(id: Long): TodoList
    fun getAllTodoList(): List<TodoList>
    fun updateTodoList(id: Long, todoListDTO: TodoListDTO): TodoList
    fun deleteTodoList(id: Long)
}