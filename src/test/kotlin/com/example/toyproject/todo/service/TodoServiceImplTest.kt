package com.example.toyproject.todo.service

import com.example.toyproject.todo.entity.DTO
import com.example.toyproject.todo.entity.Todo
import com.example.toyproject.todo.exception.BaseResponseCode
import com.example.toyproject.todo.exception.CustomException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.data.repository.CrudRepository
import java.util.*

@AnnotationSpec.Test
class TodoServiceImplTest constructor(
    private val todoRepository: CrudRepository<Todo, UUID> = mockk(),
    private val todoService: TodoService = TodoServiceImpl(todoRepository),
): BehaviorSpec({

    afterContainer {
        clearAllMocks()
    }

    val testDTO = DTO(null, "TEST")
    val targetUUID: UUID = UUID.randomUUID()

    Given("create Todo Test"){
        every { todoRepository.save(any()) } returns mockk()
        `when`("when save called"){
            todoService.createTodo(testDTO)
            then("verify Repository"){
                verify { todoRepository.save(any()) }
            }
        }
    }
    Given("When Todo already exist"){
        every { todoRepository.findById(any()) } returns Optional.of(Todo(targetUUID,"FIND"))
        `when`("when todo serched") {
            val resultDTO: DTO? = todoService.getTodo(targetUUID)
            then("it should return proper todo") {
                resultDTO?.content shouldBe  "FIND"
            }
        }
    }
    Given("When Todo not exist") {
        every { todoRepository.findById(any()) } returns Optional.empty()
        `when` ("when todo serched, ") {
            val e = shouldThrow<CustomException> {
                todoService.getTodo(targetUUID)
            }
            then("proper Exception shuld be returned") {
                e.baseResponseCode shouldBe BaseResponseCode.TODO_NOT_FOUND
            }
        }
    }
})

