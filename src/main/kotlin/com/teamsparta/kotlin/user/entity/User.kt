package com.teamsparta.kotlin.user.entity

import com.sparta.techTree.common.model.BaseTimeEntity
import com.teamsparta.kotlin.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["email"])]
)
class User(
    email: String,
    password: String,
    nickname: String,

): BaseTimeEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null

    @Column(nullable = false, length = 30, updatable = false)
    var email = email

    @Column(nullable = false, length = 100)
    var password = password

    @Column(nullable = false, length = 10)
    var nickname= nickname

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    val userRole: List<UserRoleEntity>? = null

}

fun User.toResponse(): UserResponse{
    return UserResponse(
        id = id!!,
        email = email,
        nickname = nickname,
        createdAt = this.createdAt
    )
}