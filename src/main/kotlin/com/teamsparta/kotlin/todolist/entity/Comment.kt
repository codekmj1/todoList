package com.teamsparta.kotlin.todolist.entity

import com.teamsparta.kotlin.user.entity.User
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

    //회원
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "todo_id")
    var todo: Todo? = null

    


){
    fun checkPassword(password: String): Boolean {
        return this.password == password
    }
}
