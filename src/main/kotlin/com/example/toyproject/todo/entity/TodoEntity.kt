package com.example.toyproject.todo.entity

import java.util.UUID
import javax.persistence.*


@Entity
class Todo(
    @Id
    @Column(columnDefinition = "Binary(16)")
    val id: UUID? = null,

    @Column(nullable = false, length = 100)
    val content: String
)

fun Todo.toDTO(): DTO {
    return DTO(this.id!!, this.content)
}