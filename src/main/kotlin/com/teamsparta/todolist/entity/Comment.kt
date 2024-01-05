package com.teamsparta.todolist.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
data class Comment(
    @Id @GeneratedValue
    val id: Long?,

    @Column(nullable = false)
    var writer: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "todo_id")
    var todo: Todo? = null


){
    fun checkPassword(password: String): Boolean {
        return this.password == password
    }
}
