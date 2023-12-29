package com.teamsparta.todolist.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class TodoList(
    @Id @GeneratedValue
    val id: Long?,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    var writer: String,

    @Column(nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now()
)