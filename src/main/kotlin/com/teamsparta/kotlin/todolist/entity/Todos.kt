package com.teamsparta.kotlin.todolist.entity

import com.teamsparta.kotlin.common.entity.BaseTimeEntity
import com.teamsparta.kotlin.todolist.dto.todosdto.CreateTodosRequest
import com.teamsparta.kotlin.todolist.dto.todosdto.TodosResponse
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
@Table(name = "todos")
class Todos(
    @Column(name = "title") var title: String,

    @Column(name = "content") var content: String,

    @OneToMany(mappedBy = "todos", cascade = [CascadeType.REMOVE])
    val comments: List<Comments> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: Users
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Todos.toResponse(): TodosResponse {
    return TodosResponse(
        id = id,  // null 허용
        title = title,
        content = content,
        userId = user.id,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt

    )
}