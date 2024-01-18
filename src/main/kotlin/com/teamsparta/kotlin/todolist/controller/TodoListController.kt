package com.teamsparta.kotlin.todolist.controller

import com.teamsparta.kotlin.todolist.dto.CommentDTO
import com.teamsparta.kotlin.todolist.dto.TodoListDTO
import com.teamsparta.kotlin.todolist.entity.Comment
import com.teamsparta.kotlin.todolist.entity.Todo
import com.teamsparta.kotlin.todolist.service.TodoListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/todolist")
class TodoListController(private val todoListService: TodoListService) {
    @PostMapping
    fun createTodoList(@RequestBody todoListDTO: TodoListDTO): ResponseEntity<Todo> {
        val todoList = todoListService.createTodoList(todoListDTO)
        return ResponseEntity(todoList, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getTodoList(@PathVariable id: Long): ResponseEntity<Todo> {
        val todoList = todoListService.getTodoList(id)
        return ResponseEntity(todoList, HttpStatus.OK)
    }

    @GetMapping
    fun getAllTodoList(): ResponseEntity<List<Todo>> {
        val todoList = todoListService.getAllTodoList()
        return ResponseEntity(todoList, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateTodoList(@PathVariable id: Long, @RequestBody todoListDTO: TodoListDTO): ResponseEntity<Todo> {
        val updatedTodoList = todoListService.updateTodoList(id, todoListDTO)
        return ResponseEntity(updatedTodoList, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteTodoList(@PathVariable id: Long): ResponseEntity<Void> {
        todoListService.deleteTodoList(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    //완료 처리
    @PutMapping("/{id}/complete")
    fun completeTodoList(@PathVariable id: Long): ResponseEntity<Todo> {
        val completedTodoList = todoListService.completeTodoList(id)
        return ResponseEntity(completedTodoList, HttpStatus.OK)
    }
    //댓글
    @PostMapping("/{id}/comments")
    fun createComment(@PathVariable id: Long, @RequestBody commentDTO: CommentDTO): ResponseEntity<Comment> {
        val comment = todoListService.createComment(id, commentDTO)
        return ResponseEntity(comment, HttpStatus.CREATED)
    }

    @PutMapping("/comments/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody commentDTO: CommentDTO): ResponseEntity<Comment> {
        val updatedComment = todoListService.updateComment(id, commentDTO)
        return ResponseEntity(updatedComment, HttpStatus.OK)
    }

    @DeleteMapping("/comments/{id}")
    fun deleteComment(@PathVariable id: Long, @RequestBody commentDTO: CommentDTO): ResponseEntity<Void> {
        todoListService.deleteComment(id, commentDTO)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}

