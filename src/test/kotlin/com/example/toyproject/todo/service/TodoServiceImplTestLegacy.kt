//package com.example.toyproject.todo.service
//
//import com.example.toyproject.todo.entity.DTO
//import com.example.toyproject.todo.entity.Todo
//import com.example.toyproject.todo.entity.toDTO
//import io.kotest.core.spec.AfterContainer
//import io.kotest.core.spec.style.BehaviorSpec
//import io.kotest.matchers.shouldBe
//import io.mockk.MockKAnnotations.init
//import io.mockk.clearAllMocks
//import io.mockk.every
//import io.mockk.mockk
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.data.repository.CrudRepository
//import java.util.*
//
//@SpringBootTest
//class TodoServiceImplTestLegacy constructor(
//    val todoRepository: CrudRepository<Todo, UUID> = mockk(),
//    val todoService: TodoService = TodoServiceImpl(todoRepository),
//): BehaviorSpec({
//
//    afterContainer {
//        clearAllMocks()
//    }
//
//    given("createTodo") {
//        `when`("todo를 생성하는 경우"){
//            every { todoRepository.findById() }
//            todoService.createTodo(DTO(null,"새로운 Todo 입니다."))
//            todoService.createTodo(DTO(null,"새로운 Todo 입니다."))
//            todoService.createTodo(DTO(null,"새로운 Todo 입니다."))
//            then("새로운 todo가 생성된다"){
//                val result: Todo? = todoService.getTodos().find { todo: Todo -> todo.content == "새로운 Todo 입니다." }
//                result?.content.shouldBe("새로운 Todo 입니다.")
//            }
//        }
//    }
//
//    given("deleteTodo") {
//        val todo: Todo = todoService.createTodo(DTO(null,"삭제해야 할 Todo 입니다."))
//        `when`("todo를 삭제하는 경우"){
//            todoService.deleteTodo(todo.id!!)
//            then("Todo가 삭제됩니다.") {
//            }
//        }
//    }
//
//    given("getTodos") {
//        val todo: Todo = todoService.createTodo(DTO(null,"찾아야할 Todo 입니다."))
//        val targetUUID: UUID = todo.id!!
//        `when`("Todos를 불러온 경우") {
//            val todos: MutableIterable<Todo> = todoService.getTodos()
//            then("Todo의 내용이 동일해야한다."){
//                todos.contains(Todo(targetUUID,"찾아야할 Todo 입니다."))
//            }
//        }
//    }
//
//    given("getTodo") {
//        val todo: Todo = todoService.createTodo(DTO(null,"찾아야할 Todo 입니다."))
//        val targetUUID: UUID = todo.id!!
//        todoService.createTodo(todo.toDTO())
//        `when`("Todo를 서치한 경우") {
//            val foundTodo: Todo? = todoService.getTodo(targetUUID)
//            then("Todo의 내용이 객체와 동일해야한다.") {
//            }
//        }
//    }
//
//    given("editTodo") {
//        todoService.deleteAll()
//        val todo: Todo = todoService.createTodo(DTO(null,"수정해야할 Todo 입니다."))
//        val targetUUID: UUID = todo.id!!
//        todoService.createTodo(todo.toDTO())
//        `when`("Todo를 수정한 경우"){
//            todoService.editTodo(targetUUID,"수정된 내용입니다.")
//            then("Todo의 내용이 수정된 내용이어야 한다.") {
//                val resultTodo: Todo? = todoService.getTodo(targetUUID)
//            }
//        }
//    }
//})
//
