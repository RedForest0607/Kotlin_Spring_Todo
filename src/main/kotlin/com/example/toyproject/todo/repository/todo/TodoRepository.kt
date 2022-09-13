package com.example.toyproject.todo.repository.todo

import java.util.*

interface TodoRepository <Todo, UUID>{
    fun save(todo: Todo)
    fun deleteById(id: UUID)
    fun findAll(): MutableIterable<Todo>
    fun findById(id: UUID): Optional<Todo>
    fun deleteAll()
}