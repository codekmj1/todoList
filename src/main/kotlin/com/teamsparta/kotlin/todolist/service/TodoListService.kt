package com.teamsparta.kotlin.todolist.service

import com.teamsparta.kotlin.todolist.dto.commentsdto.CreateCommentsRequest
import com.teamsparta.kotlin.todolist.dto.commentsdto.UpdateCommentsRequest
import com.teamsparta.kotlin.todolist.dto.todosdto.CreateTodosRequest
import com.teamsparta.kotlin.todolist.dto.todosdto.UpdateTodosRequest
import com.teamsparta.kotlin.todolist.entity.Comments
import com.teamsparta.kotlin.todolist.entity.Todos

interface TodoListService {
    fun createTodo(createTodoRequest: CreateTodosRequest, userId: Long): Todos
    fun updateTodo(updateTodoRequest: UpdateTodosRequest, todoId: Long, userId: Long): Todos
    fun createComment(createCommentRequest: CreateCommentsRequest, todoId: Long, userId: Long): Comments
    fun updateComment(updateCommentRequest: UpdateCommentsRequest, commentId: Long, userId: Long): Comments
    fun deleteTodo(todoId: Long, userId: Long)
    fun deleteComment(commentId: Long, userId: Long)

}