package com.teamsparta.todolist.service

import com.teamsparta.todolist.entity.Todo
import com.teamsparta.todolist.repository.TodoListRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class TodoServiceTest {

    private val mockRepository = mock(TodoListRepository::class.java)
    private val todoListService = TodoListServiceImpl(mockRepository)

    @Test
    fun getAllTodoList() {
        val todo = Todo(1L, "Test Title", "Test Content", "Test Writer")
        `when`(mockRepository.findAllByOrderByCreatedDateDesc()).thenReturn(listOf(todo))

        val result = todoListService.getAllTodoList()

        assertEquals(1, result.size)
        assertEquals("Test Title", result[0].title)
    }
}