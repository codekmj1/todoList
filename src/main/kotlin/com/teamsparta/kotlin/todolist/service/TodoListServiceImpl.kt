package com.teamsparta.kotlin.todolist.service


import com.teamsparta.kotlin.todolist.dto.commentsdto.CreateCommentsRequest
import com.teamsparta.kotlin.todolist.dto.commentsdto.UpdateCommentsRequest
import com.teamsparta.kotlin.todolist.dto.todosdto.CreateTodosRequest
import com.teamsparta.kotlin.todolist.dto.todosdto.UpdateTodosRequest
import com.teamsparta.kotlin.todolist.entity.Comments
import com.teamsparta.kotlin.todolist.entity.Todos
import com.teamsparta.kotlin.todolist.repository.CommentsRepository
import com.teamsparta.kotlin.todolist.repository.TodosRepository
import com.teamsparta.kotlin.todolist.repository.UsersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoListServiceImpl(
    private val todosRepository: TodosRepository,
    private val commentsRepository: CommentsRepository,
    private val usersRepository: UsersRepository
) : TodoListService {

    @Transactional
    override fun createTodo(createTodoRequest: CreateTodosRequest, userId: Long): Todos {
        val user = usersRepository.findById(userId).orElseThrow { IllegalArgumentException("User is not logged in") }

        if (createTodoRequest.title.isBlank() || createTodoRequest.content.isBlank()) {
            throw IllegalArgumentException("Title or content cannot be blank")
        }

        val todo = Todos(
            title = createTodoRequest.title,
            content = createTodoRequest.content,
            user = user
        )

        return todosRepository.save(todo)
    }

    @Transactional
    override fun updateTodo(updateTodoRequest: UpdateTodosRequest, todoId: Long, userId: Long): Todos {
        val todo = todosRepository.findById(todoId).orElseThrow { IllegalArgumentException("Todo does not exist") }
        val user = usersRepository.findById(userId).orElseThrow { IllegalArgumentException("User is not logged in") }

        if (todo.user != user) {
            throw IllegalArgumentException("User does not have permission to update this Todo")
        }

        updateTodoRequest.title?.let {
            if (it.isBlank()) throw IllegalArgumentException("Title cannot be blank")
            todo.title = it
        }

        updateTodoRequest.content?.let {
            if (it.isBlank()) throw IllegalArgumentException("Content cannot be blank")
            todo.content = it
        }

        return todo
    }
    @Transactional
    override fun deleteTodo(todoId: Long, userId: Long) {
        val todo = todosRepository.findById(todoId).orElseThrow { IllegalArgumentException("Todo does not exist") }
        val user = usersRepository.findById(userId).orElseThrow { IllegalArgumentException("User is not logged in") }

        if (todo.user != user) {
            throw IllegalArgumentException("User does not have permission to delete this Todo")
        }

        todosRepository.delete(todo)
    }
    @Transactional
    override fun createComment(createCommentRequest: CreateCommentsRequest, todoId: Long, userId: Long): Comments {
        val todo = todosRepository.findById(todoId).orElseThrow { IllegalArgumentException("Todo does not exist") }
        val user = usersRepository.findById(userId).orElseThrow { IllegalArgumentException("User does not exist") }

        if (createCommentRequest.content.isBlank()) {
            throw IllegalArgumentException("Content cannot be blank")
        }

        val comment = Comments(
            content = createCommentRequest.content,
            todos = todo,
            user = user
        )

        return commentsRepository.save(comment)
    }

    @Transactional
    override fun updateComment(updateCommentRequest: UpdateCommentsRequest, commentId: Long, userId: Long): Comments {
        val comment = commentsRepository.findById(commentId).orElseThrow { IllegalArgumentException("Comment does not exist") }
        val user = usersRepository.findById(userId).orElseThrow { IllegalArgumentException("User is not logged in") }

        if (comment.user != user) {
            throw IllegalArgumentException("User does not have permission to update this Comment")
        }

        updateCommentRequest.content.let {
            if (it.isBlank()) throw IllegalArgumentException("Content cannot be blank")
            comment.content = it
        }

        return comment
    }
    @Transactional
    override fun deleteComment(commentId: Long, userId: Long) {
        val comment = commentsRepository.findById(commentId).orElseThrow { IllegalArgumentException("Comment does not exist") }
        val user = usersRepository.findById(userId).orElseThrow { IllegalArgumentException("User is not logged in") }

        if (comment.user != user) {
            throw IllegalArgumentException("User does not have permission to delete this Comment")
        }

        commentsRepository.delete(comment)
    }
}