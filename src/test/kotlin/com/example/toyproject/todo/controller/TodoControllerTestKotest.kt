//package com.example.toyproject.todo.controller
//
//import com.example.toyproject.todo.entity.Todo
//import com.example.toyproject.todo.service.TodoService
//import io.kotest.core.spec.style.FunSpec
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
//import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
//import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import java.util.*
//
//@AutoConfigureRestDocs
//@SpringBootTest
//@ExtendWith(RestDocumentationExtension::class)
//class TodoControllerTestKotest(
//    @Autowired private val mockMvc: MockMvc,
//    @MockBean private val todoService: TodoService
//) : FunSpec({
//
//    val todo: Todo = Todo(UUID.randomUUID(), "Mock")
//
//    test("newTodo") {
//        val uri: String = "/todo"
//        mockMvc.perform(
//            RestDocumentationRequestBuilders.post(uri)
//                .queryParam("할일 입니다.")
//        )
//
//    }
//
//
//
//    test("getTodos") {
//        val uri: String = "/todo/list"
//        mockMvc.perform(
//            RestDocumentationRequestBuilders.get(uri)
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andDo(MockMvcResultHandlers.print())
//            .andDo(
//                document(
//                    "get-list",
//                    responseFields(
//                        fieldWithPath("id").description("Todo Id"),
//                        fieldWithPath("content").description("Todo Content")
//                    )
//                )
//            )
//    }
//
//
//    test("getTodo") { }
//
//    test("putTodo") { }
//
//    test("deleteTodo") { }
//
//    test("todoService") { }
//})