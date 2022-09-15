package com.example.toyproject.todo.service

import com.example.toyproject.todo.entity.DTO
import com.example.toyproject.todo.entity.Todo
import com.example.toyproject.todo.entity.toDTO
import com.example.toyproject.todo.entity.toEntity
import com.example.toyproject.todo.exception.BaseResponseCode
import com.example.toyproject.todo.exception.CustomException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoServiceImpl(
    @Autowired val todoRepository: CrudRepository<Todo, UUID>
): TodoService{

    override fun createTodo(todoDTO: DTO): Todo {
        todoDTO.id = UUID.randomUUID()
        return todoRepository.save(todoDTO.toEntity())
    }

    override fun deleteTodo(id: UUID): DTO? {
        val tempTodo: DTO = todoRepository.findByIdOrNull(id)?.toDTO() ?: throw CustomException(BaseResponseCode.TODO_NOT_FOUND)
        todoRepository.deleteById(id)
        return tempTodo
    }

    override fun getTodos(): MutableIterable<Todo> {
        return todoRepository.findAll()

    }

    override fun getTodo(id: UUID): DTO? {
        return todoRepository.findByIdOrNull(id)?.toDTO() ?: throw CustomException(BaseResponseCode.TODO_NOT_FOUND)
    }

    override fun editTodo(DTO: DTO): DTO {
        if (todoRepository.findById(DTO.id!!).isPresent) {
            return todoRepository.save(DTO.toEntity()).toDTO()
        } else {
            throw IndexOutOfBoundsException()  //오류 처리
        }
    }

    override fun deleteAll() {
        todoRepository.deleteAll()
    }
}