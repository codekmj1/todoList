package com.teamsparta.todolist.service

import com.teamsparta.todolist.dto.CommentDTO
import com.teamsparta.todolist.dto.TodoListDTO
import com.teamsparta.todolist.entity.Comment
import com.teamsparta.todolist.entity.Todo

interface TodoListService {
    fun createTodoList(todoListDTO: TodoListDTO): Todo
    fun getTodoList(id: Long): Todo
    fun getAllTodoList(): List<Todo>
    fun updateTodoList(id: Long, todoListDTO: TodoListDTO): Todo
    fun deleteTodoList(id: Long)
    
    //완료표시
    fun completeTodoList(id: Long): Todo
    //댓글
    fun createComment(id: Long, commentDTO: CommentDTO): Comment
    fun updateComment(id: Long, commentDTO: CommentDTO): Comment
    fun deleteComment(id: Long, commentDTO: CommentDTO)
}


