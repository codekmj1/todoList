package com.teamsparta.todolist.controller

import com.teamsparta.todolist.dto.TodoListDTO
import com.teamsparta.todolist.entity.Todo
import com.teamsparta.todolist.service.TodoListService
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
}

