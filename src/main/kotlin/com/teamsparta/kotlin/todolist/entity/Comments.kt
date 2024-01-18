package com.teamsparta.kotlin.todolist.entity

import com.teamsparta.kotlin.common.entity.BaseTimeEntity
import com.teamsparta.kotlin.todolist.dto.todosdto.TodosResponse
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(name = "comments")
class Comments(
    @Column(name = "content") var content: String,

    @ManyToOne @JoinColumn(name = "todo_id") val todos: Todos?,

    //연관 관계 지어줌
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: Users?
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

