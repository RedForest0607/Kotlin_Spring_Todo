package com.example.toyproject.todo.entity

import java.util.UUID
import javax.persistence.*


@Entity
@Table(name = "TODOS")
class Todo(
    @Id
    @Column(columnDefinition = "Binary(16)")
    val id: UUID? = null,

    val content: String
)

fun Todo.toDTO(): DTO {
    return DTO(this.id!!, this.content)
}