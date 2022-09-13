package com.example.toyproject.todo.entity

import java.util.UUID

data class DTO(
    var id: UUID?,
    var content: String
)

fun DTO.toEntity(): Todo {
    return Todo(id, content)
}
