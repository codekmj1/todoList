package com.teamsparta.kotlin.todolist.entity

import com.teamsparta.kotlin.user.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Todo(
    @Id @GeneratedValue
    val id: Long?,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    var writer: String,

    @Column(nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var completed: Boolean = false,
    //회원
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User
)

