package com.example.toyproject.todo.repository.todo

import com.example.toyproject.todo.entity.DTO
import com.example.toyproject.todo.entity.Todo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CrudTodoRepository : CrudRepository<Todo, UUID>{
}