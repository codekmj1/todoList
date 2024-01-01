package com.teamsparta.todolist.service

import com.teamsparta.todolist.dto.TodoListDTO
import com.teamsparta.todolist.entity.Todo
import com.teamsparta.todolist.repository.TodoListRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
@Transactional
class TodoListServiceImpl(private val todoListRepository: TodoListRepository) : TodoListService {

    override fun createTodoList(todoListDTO: TodoListDTO): Todo {
        val todo = Todo(null, todoListDTO.title, todoListDTO.content, todoListDTO.writer)
        return todoListRepository.save(todo)
    }

    override fun getTodoList(id: Long): Todo {
        return todoListRepository.findById(id).orElseThrow { IllegalArgumentException("TodoList not found.") }
    }

    override fun getAllTodoList(): List<Todo> {
        return todoListRepository.findAllByOrderByCreatedDateDesc()
    }

    override fun updateTodoList(id: Long, todoListDTO: TodoListDTO): Todo {
        val todoList = getTodoList(id)
        todoList.title = todoListDTO.title
        todoList.content = todoListDTO.content
        todoList.writer = todoListDTO.writer
        return todoList
    }

    override fun deleteTodoList(id: Long) {
        todoListRepository.deleteById(id)
    }
}