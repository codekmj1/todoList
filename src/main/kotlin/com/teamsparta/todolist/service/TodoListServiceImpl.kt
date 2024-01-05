package com.teamsparta.todolist.service

import com.teamsparta.exception.PasswordNotMatchException
import com.teamsparta.todolist.dto.CommentDTO
import com.teamsparta.todolist.dto.TodoListDTO
import com.teamsparta.todolist.entity.Comment
import com.teamsparta.todolist.entity.Todo
import com.teamsparta.todolist.repository.CommentRepository
import com.teamsparta.todolist.repository.TodoListRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
@Transactional
class TodoListServiceImpl(
    private val todoListRepository: TodoListRepository,
    private val commentRepository: CommentRepository
) : TodoListService {

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
    //완료표시
    override fun completeTodoList(id: Long): Todo {
        val todoList = getTodoList(id)
        todoList.completed = true
        return todoList
    }
    //댓글
    override fun createComment(id: Long, commentDTO: CommentDTO): Comment {
        val todoList = getTodoList(id)
        val comment = Comment(null, commentDTO.writer, commentDTO.password, commentDTO.content, todo = todoList)
        return commentRepository.save(comment)
    }

    override fun updateComment(id: Long, commentDTO: CommentDTO): Comment {
        val comment = commentRepository.findById(id).orElseThrow { IllegalArgumentException("Comment not found.") }
        if (!comment.checkPassword(commentDTO.password)) {
            throw PasswordNotMatchException()
        }
        comment.content = commentDTO.content
        return comment
    }

    override fun deleteComment(id: Long, commentDTO: CommentDTO) {
        val comment = commentRepository.findById(id).orElseThrow { IllegalArgumentException("Comment not found.") }
        if (!comment.checkPassword(commentDTO.password)) {
            throw PasswordNotMatchException()
        }
        commentRepository.deleteById(id)
    }
}