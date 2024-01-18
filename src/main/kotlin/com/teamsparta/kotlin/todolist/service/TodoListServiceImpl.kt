package com.teamsparta.kotlin.todolist.service


import com.teamsparta.kotlin.common.exception.PasswordNotMatchException
import com.teamsparta.kotlin.todolist.dto.CommentDTO
import com.teamsparta.kotlin.todolist.dto.TodoListDTO
import com.teamsparta.kotlin.todolist.entity.Comment
import com.teamsparta.kotlin.todolist.entity.Todo
import com.teamsparta.kotlin.todolist.repository.CommentRepository
import com.teamsparta.kotlin.todolist.repository.TodoListRepository
import com.teamsparta.kotlin.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class TodoListServiceImpl(
    private val todoListRepository: TodoListRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository
) : TodoListService {

    @Transactional
    override fun createTodoList(userId: Long, todoListDTO: TodoListDTO): Todo {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found.") }
        val todo = Todo(null, todoListDTO.title, todoListDTO.content, user = user)
        return todoListRepository.save(todo)
    }

    @Transactional
    override fun getTodoList(id: Long): Todo {
        return todoListRepository.findById(id).orElseThrow { IllegalArgumentException("TodoList not found.") }
    }

    @Transactional
    override fun getAllTodoList(): List<Todo> {
        return todoListRepository.findAllByOrderByCreatedDateDesc()
    }

    @Transactional
    override fun updateTodoList(id: Long, todoListDTO: TodoListDTO): Todo {
        val todoList = getTodoList(id)
        todoList.title = todoListDTO.title
        todoList.content = todoListDTO.content
        return todoList
    }

    @Transactional
    override fun deleteTodoList(id: Long) {
        todoListRepository.deleteById(id)
    }

    //완료표시
    @Transactional
    override fun completeTodoList(id: Long): Todo {
        val todoList = getTodoList(id)
        todoList.completed = true
        return todoList
    }

    //댓글
    @Transactional
    override fun createComment(userId: Long, id: Long, commentDTO: CommentDTO): Comment {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found.") }
        val todoList = getTodoList(id)
        val comment = Comment(null, commentDTO.writer, commentDTO.password, commentDTO.content, user = user, todo = todoList)
        return commentRepository.save(comment)
    }

    @Transactional
    override fun updateComment(id: Long, commentDTO: CommentDTO): Comment {
        val comment = commentRepository.findById(id).orElseThrow { IllegalArgumentException("Comment not found.") }
        if (!comment.checkPassword(commentDTO.password)) {
            throw PasswordNotMatchException()
        }
        comment.content = commentDTO.content
        return comment
    }

    @Transactional
    override fun deleteComment(id: Long, commentDTO: CommentDTO) {
        val comment = commentRepository.findById(id).orElseThrow { IllegalArgumentException("Comment not found.") }
        if (!comment.checkPassword(commentDTO.password)) {
            throw PasswordNotMatchException()
        }
        commentRepository.deleteById(id)
    }
}