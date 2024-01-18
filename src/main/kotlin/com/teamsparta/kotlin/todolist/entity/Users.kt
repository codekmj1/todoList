package com.teamsparta.kotlin.todolist.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.teamsparta.kotlin.common.entity.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(
    name = "users",
    uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["email"])]
)
class Users(
    email: String,
    password: String,
    nickname: String
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    @JsonIgnore
    val userRole: List<UserRoleEntity>? = null

}