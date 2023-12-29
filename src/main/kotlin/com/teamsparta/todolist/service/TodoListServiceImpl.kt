package com.teamsparta.todolist.service

import com.teamsparta.todolist.dto.TodoListDTO
import com.teamsparta.todolist.entity.TodoList
import com.teamsparta.todolist.repository.TodoListRepository
import org.springframework.stereotype.Service

@Service
class TodoListServiceImpl(private val todoListRepository: TodoListRepository) : TodoListService {

    override fun createTodoList(todoListDTO: TodoListDTO): TodoList {
        val todoList = TodoList(null, todoListDTO.title, todoListDTO.content, todoListDTO.writer)
        return todoListRepository.save(todoList)
    }

    override fun getTodoList(id: Long): TodoList {
        return todoListRepository.findById(id).orElseThrow { IllegalArgumentException("TodoList not found.") }
    }

    override fun getAllTodoList(): List<TodoList> {
        return todoListRepository.findAllByOrderByCreatedDateDesc()
    }

    override fun updateTodoList(id: Long, todoListDTO: TodoListDTO): TodoList {
        val todoList = getTodoList(id)
        todoList.title = todoListDTO.title
        todoList.content = todoListDTO.content
        todoList.writer = todoListDTO.writer
        return todoListRepository.save(todoList)
    }

    override fun deleteTodoList(id: Long) {
        todoListRepository.deleteById(id)
    }
}