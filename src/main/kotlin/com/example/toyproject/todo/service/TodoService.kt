package com.example.toyproject.todo.service

import com.example.toyproject.todo.entity.DTO
import com.example.toyproject.todo.entity.Todo
import java.util.*

interface TodoService {
    fun createTodo(todoDTO: DTO): Todo
    fun deleteTodo(id: UUID): DTO?
    fun getTodos(): MutableIterable<Todo>
    fun getTodo(id: UUID): DTO?
    fun editTodo(DTO: DTO): DTO
    fun deleteAll()
}