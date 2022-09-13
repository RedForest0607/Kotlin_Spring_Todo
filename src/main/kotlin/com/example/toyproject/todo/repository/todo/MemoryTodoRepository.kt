package com.example.toyproject.todo.repository.todo

import com.example.toyproject.todo.entity.Todo
/*
class MemoryTodoRepository : TodoRepository {

    private val todos = mutableListOf<Todo>()

    override fun save(content: String){
        val todo = Todo(todos.size, content)
        todos.add(todo)
    }

    override fun deleteById(id: Int) {
        todos.removeAt(id)
    }

    override fun findAll(): List<Todo> {
        return todos
    }

    override fun findById(id: Int): Todo {
        return todos[id]
    }

    override fun edit(id: Int, content: String) {
        todos[id].content = content
    }

    /*
       UTIL
    */
    override fun deleteAll() {
        todos.clear()
    }
    override fun getNumberOfElements(): Int {
        return todos.size
    }
}
*/
