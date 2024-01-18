package com.teamsparta.kotlin.todolist.controller


import com.teamsparta.kotlin.common.auth.JwtTokenProvider
import com.teamsparta.kotlin.common.dto.CustomUser
import com.teamsparta.kotlin.todolist.dto.commentsdto.CommentsResponse
import com.teamsparta.kotlin.todolist.dto.commentsdto.CreateCommentsRequest
import com.teamsparta.kotlin.todolist.dto.commentsdto.UpdateCommentsRequest
import com.teamsparta.kotlin.todolist.dto.todosdto.CreateTodosRequest
import com.teamsparta.kotlin.todolist.dto.todosdto.TodosResponse
import com.teamsparta.kotlin.todolist.dto.todosdto.UpdateTodosRequest
import com.teamsparta.kotlin.todolist.service.TodoListService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TodoListController(
    private val todoListService: TodoListService,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @PostMapping("/todos")
    fun createTodo(@RequestHeader("Authorization") authHeader: String,
                   @RequestBody createTodoRequest: CreateTodosRequest): ResponseEntity<Map<String, Any>> {
        val token = authHeader.removePrefix("Bearer ")
        if (!jwtTokenProvider.validateToken(token)) {
            throw BadCredentialsException("Invalid token")
        }
        val userId = (jwtTokenProvider.getAuthentication(token).principal as CustomUser).id
        val todo = todoListService.createTodo(createTodoRequest, userId)
        val todoResponse = TodosResponse(todo.id, todo.title, todo.content, userId, todo.createdAt, todo.updatedAt)
        return ResponseEntity.ok(mapOf("message" to "Todo created successfully.", "data" to todoResponse))
    }

    @PutMapping("/todos/{id}")
    fun updateTodo(@RequestHeader("Authorization") authHeader: String,
                   @RequestBody updateTodoRequest: UpdateTodosRequest,
                   @PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        val token = authHeader.removePrefix("Bearer ")
        if (!jwtTokenProvider.validateToken(token)) {
            throw BadCredentialsException("Invalid token")
        }
        val userId = (jwtTokenProvider.getAuthentication(token).principal as CustomUser).id
        val todo = todoListService.updateTodo(updateTodoRequest, id, userId)
        val todoResponse = TodosResponse(todo.id, todo.title, todo.content, userId, todo.createdAt, todo.updatedAt)
        return ResponseEntity.ok(mapOf("message" to "Todo updated successfully.", "data" to todoResponse))
    }
    @DeleteMapping("/todos/{id}")
    fun deleteTodo(@RequestHeader("Authorization") authHeader: String,
                   @PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        val token = authHeader.removePrefix("Bearer ")
        if (!jwtTokenProvider.validateToken(token)) {
            throw BadCredentialsException("Invalid token")
        }
        val userId = (jwtTokenProvider.getAuthentication(token).principal as CustomUser).id
        todoListService.deleteTodo(id, userId)
        return ResponseEntity.ok(mapOf("message" to "Todo deleted successfully."))
    }

    @PostMapping("/todos/{todoId}/comments")
    fun createComment(@RequestHeader("Authorization") authHeader: String,
                      @RequestBody createCommentRequest: CreateCommentsRequest,
                      @PathVariable todoId: Long): ResponseEntity<Map<String, Any>> {
        val token = authHeader.removePrefix("Bearer ")
        if (!jwtTokenProvider.validateToken(token)) {
            throw BadCredentialsException("Invalid token")
        }
        val userId = (jwtTokenProvider.getAuthentication(token).principal as CustomUser).id
        val comment = todoListService.createComment(createCommentRequest, todoId, userId)
        val commentResponse = CommentsResponse(comment.id, userId, todoId, comment.content, comment.createdAt, comment.updatedAt)
        return ResponseEntity.ok(mapOf("message" to "Comment created successfully.", "data" to commentResponse))
    }

    @PutMapping("/todos/{todoId}/comments/{id}")
    fun updateComment(@RequestHeader("Authorization") authHeader: String,
                      @RequestBody updateCommentRequest: UpdateCommentsRequest,
                      @PathVariable todoId: Long,
                      @PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        val token = authHeader.removePrefix("Bearer ")
        if (!jwtTokenProvider.validateToken(token)) {
            throw BadCredentialsException("Invalid token")
        }
        val userId = (jwtTokenProvider.getAuthentication(token).principal as CustomUser).id
        val comment = todoListService.updateComment(updateCommentRequest, id, userId)
        val commentResponse = CommentsResponse(comment.id, userId, todoId, comment.content, comment.createdAt, comment.updatedAt)
        return ResponseEntity.ok(mapOf("message" to "Comment updated successfully.", "data" to commentResponse))
    }
    @DeleteMapping("/todos/{todoId}/comments/{id}")
    fun deleteComment(@RequestHeader("Authorization") authHeader: String,
                      @PathVariable todoId: Long,
                      @PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        val token = authHeader.removePrefix("Bearer ")
        if (!jwtTokenProvider.validateToken(token)) {
            throw BadCredentialsException("Invalid token")
        }
        val userId = (jwtTokenProvider.getAuthentication(token).principal as CustomUser).id
        todoListService.deleteComment(id, userId)
        return ResponseEntity.ok(mapOf("message" to "Comment deleted successfully."))
    }
}
