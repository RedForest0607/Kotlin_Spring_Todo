package com.example.toyproject.todo.repository.todo

import com.example.toyproject.todo.entity.Todo
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
@Transactional
internal class TodoRepositoryTest  @Autowired constructor(
    private var todoRepository: CrudRepository<Todo, UUID>
    ) {
    @BeforeAll
    fun setUp() {
        println(">>테스트 시작")
    }



    /*
        POST
     */
    @AfterEach
    fun afterEach() {
        todoRepository.deleteAll()
    }

    @Test
    @DisplayName("Todo 작성 테스트")
    fun saveTodo() {
//		given
        val randomUuid = UUID.randomUUID()
//		when
        todoRepository.save(Todo(randomUuid,"할일 입니다."))
//		then
        Assertions.assertThat(todoRepository.findById(randomUuid).get().content).isEqualTo("할일 입니다.")
    }

    /*
        GET
    */
    @Test
    @DisplayName("Todo 하나만 가져오기 테스트")
    fun getTodo() {
        todoRepository.deleteAll()
//		given
        val randomUuid = UUID.randomUUID()
        todoRepository.save(Todo(randomUuid, "첫번째 할일 입니다."))
//		when
        val todoResult: Optional<Todo> = todoRepository.findById(randomUuid)
//		then
        Assertions.assertThat(todoResult.get().content).isEqualTo("첫번째 할일 입니다.")
    }

    @Test
    @DisplayName("Todo 비어있는 todo 목록에서 하나를 가져오는 경우")
    fun getTodoFromNoTodos() {
//		given
        val randomUuid = UUID.randomUUID()
//		when
//		then
        assertThrows(IndexOutOfBoundsException::class.java ){
            todoRepository.findById(randomUuid)
        }
    }

    @Test
    @DisplayName("Todo리스트 불러오기 테스트")
    fun findAll() {
//		given
        todoRepository.save(Todo(UUID.randomUUID(),"첫번째 할일 입니다."))
        todoRepository.save(Todo(UUID.randomUUID(),"두번째 할일 입니다."))
        todoRepository.save(Todo(UUID.randomUUID(),"세번째 할일 입니다."))

//		when
        val todoList : MutableIterable<Todo> = todoRepository.findAll()
//		then
        Assertions.assertThat(todoList).isEqualTo(todoRepository.findAll())
    }

    @Test
    @DisplayName("Todo 하나만 가져올 때, 해당하는 index의 Todo가 없는 경우")
    fun getTodoFromOutOfindex() {
//		given
        todoRepository.save(Todo(UUID.randomUUID(),"첫번째 할일 입니다."))
        todoRepository.save(Todo(UUID.randomUUID(),"두번째 할일 입니다."))
//		when
        assertThrows(IndexOutOfBoundsException::class.java ) {
            val todoResult: Optional<Todo> = todoRepository.findById(UUID.randomUUID())
        }
//		then
    }

    /*
        DELETE
    */
    @Test
    @DisplayName("Todo 삭제 테스트")
    fun deleteTodo() {
//		given
        val targetUUID = UUID.randomUUID()
        todoRepository.save(Todo(targetUUID,"삭제해야 할 todo입니다."))
//		when
        todoRepository.deleteById(targetUUID)
//		then
        assertThrows(IndexOutOfBoundsException::class.java){todoRepository.findById(UUID.randomUUID())}

    }

    @Test
    @DisplayName("Todo 삭제 실패 테스트 - todo가 없는 경우")
    fun deleteTodoWithNoTodos() {
//		given
//		when

//		then
        assertThrows(IndexOutOfBoundsException::class.java){todoRepository.deleteById(UUID.randomUUID())}
    }

    /*
        PUT
     */
    @Test
    @DisplayName("Todo 수정 테스트")
    fun editTodo(){
//      given
        val targetUUID = UUID.randomUUID()
        todoRepository.save(Todo(targetUUID, "수정해야 할 todo입니다."))
//		when
        todoRepository.save(Todo(targetUUID, "수정된 todo입니다."))
//		then
        Assertions.assertThat(todoRepository.findById(targetUUID).get().content).isEqualTo("수정된 todo입니다.")
    }
    @Test
    @DisplayName("Todo 수정 테스트")
    fun editTodoOutOfIndex(){
//      given
//		when
//		then
        assertThrows(IndexOutOfBoundsException::class.java){todoRepository.save(Todo(UUID.randomUUID(),"수정할 todo가 없습니다."))}
    }

    /*
        UTIL
    */
    @Test
    @DisplayName("Todo 사이즈 가져오기")
    fun getSizeOfEmptyTodos() {
//		given
//		when
//		then
        Assertions.assertThat(todoRepository.count()).isEqualTo(0)
    }

    @Test
    @DisplayName("Todo가 있는 경우 사이즈 가져오기")
    fun getSizeOfTodos() {
//        given, when
        todoRepository.save(Todo(UUID.randomUUID(),"첫번째 할일 입니다."))
        todoRepository.save(Todo(UUID.randomUUID(),"두번째 할일 입니다."))
        todoRepository.save(Todo(UUID.randomUUID(),"세번째 할일 입니다."))
//        then
        Assertions.assertThat(todoRepository.count()).isEqualTo(3)
    }

    @AfterAll
    fun teardown() {
        println(">>테스트 종료")
    }

}