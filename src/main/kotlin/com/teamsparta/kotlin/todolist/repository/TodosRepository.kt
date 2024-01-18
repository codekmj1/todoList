package com.teamsparta.kotlin.todolist.repository

import com.teamsparta.kotlin.todolist.entity.Todos
import org.springframework.data.jpa.repository.JpaRepository

interface TodosRepository : JpaRepository<Todos, Long> {

}


