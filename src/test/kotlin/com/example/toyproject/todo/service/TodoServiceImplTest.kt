package com.example.toyproject.todo.service

import com.example.toyproject.todo.entity.DTO
import com.example.toyproject.todo.entity.Todo
import com.example.toyproject.todo.entity.toDTO
import com.example.toyproject.todo.exception.BaseResponseCode
import com.example.toyproject.todo.exception.CustomException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.AfterContainer
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import io.mockk.MockKAnnotations.init
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.CrudRepository
import java.util.*
import kotlin.reflect.typeOf

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

    Given("save 테스트"){
        every { todoRepository.save(any()) } returns mockk()
        `when`("save 호출했을 때"){
            todoService.createTodo(testDTO)
            then("Repository까지 잘 작동했는지 평가"){
                verify { todoRepository.save(any()) }
            }
        }
    }
    Given("Todo가 이미 존재하는 상황에서"){
        every { todoRepository.findById(any()) } returns Optional.of(Todo(targetUUID,"FIND"))
        `when`("todo를 검색 한다면,") {
            val resultDTO: DTO? = todoService.getTodo(targetUUID)
            then("해당하는 Todo가 나와야 한다.") {
                resultDTO?.content shouldBe  "FIND"
            }
        }
    }
    Given("Todo가 존재하지 않는 상황에서는") {
        every { todoRepository.findById(any()) } returns Optional.empty()
        `when` ("todo를 검색 한다면, ") {
            val e = shouldThrow<CustomException> {
                todoService.getTodo(targetUUID)
            }
            then("Exception이 터져야 한다.") {
                e.baseResponseCode shouldBe BaseResponseCode.TODO_NOT_FOUND
            }
        }
    }
})

