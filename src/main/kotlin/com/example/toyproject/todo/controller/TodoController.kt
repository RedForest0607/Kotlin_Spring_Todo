package com.example.toyproject.todo.controller

import com.example.toyproject.todo.entity.DTO
import com.example.toyproject.todo.entity.Todo
import com.example.toyproject.todo.exception.BaseResponseCode
import com.example.toyproject.todo.exception.CustomException
import com.example.toyproject.todo.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@Api(tags = ["Todo List"])
@RestController
@RequestMapping("/todo")
class TodoController @Autowired constructor(val todoService: TodoService) {

    @ApiOperation(value = "새로운 Todo")
    @Operation(summary = "new Todo API")
    @PostMapping
    fun newTodo(@ApiParam(value = "Todo 내용") @RequestBody todoDTO: DTO): Todo { //Refactor
        return todoService.createTodo(todoDTO)
    }

    @ApiOperation(value = "Todo List 가져오기")
    @Operation(summary = "new Todo")
    @GetMapping("/list")
    fun getTodos(): MutableIterable<Todo> {
        return todoService.getTodos()
    }

    @ApiOperation(value = "Todo 가져오기")
    @GetMapping("/{id}")
    fun getTodo(@ApiParam(value = "Todo id")@PathVariable("id")id: UUID): Todo? {
        return todoService.getTodo(id) ?: throw CustomException(BaseResponseCode.TODO_NOT_FOUND)
    }

    @ApiOperation(value = "Todo 수정하기")
    @PutMapping("/{id}")
    fun putTodo(@ApiParam(value = "수정할 Todo id") @PathVariable("id")id: UUID,
                @ApiParam(value = "수정할Todo 내용") @RequestBody content: String): Todo {
        return todoService.editTodo(id, content)
    }

    @ApiOperation(value = "Todo 삭제하기")
    @DeleteMapping("/{id}")
    fun deleteTodo(@ApiParam(value = "삭제할 Todo id") @PathVariable("id") id: UUID): DTO? {
        return todoService.deleteTodo(id) ?: throw CustomException(BaseResponseCode.TODO_NOT_FOUND)
    }
}
