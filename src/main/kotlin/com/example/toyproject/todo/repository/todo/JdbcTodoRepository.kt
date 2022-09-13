//package com.example.toyproject.todo.repository.todo
//
//import com.example.toyproject.todo.entity.Todo
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.jdbc.core.JdbcTemplate
//import org.springframework.jdbc.core.RowMapper
//import org.springframework.stereotype.Repository
//import java.sql.ResultSet
//import java.util.*
//
//class JdbcTodoRepository(
//    @Autowired val jdbcTemplate: JdbcTemplate
//) : CrudTodoRepository<Todo, UUID> {
//
//    val mapper = RowMapper<Todo> { rs: ResultSet, rowId: Int ->
//        Todo(
//            rs.getObject("id") as UUID?,
//            rs.getString("content")
//        )
//    }
//
//    override fun save(todo: Todo) {
//        jdbcTemplate.update(
//            "INSERT INTO todos (id, content) values (?,?)",
//            todo.id,
//            todo.content
//        )
//    }
//
//    override fun deleteById(id: UUID) {
//        jdbcTemplate.query(
//            "DELETE FROM todos WHERE id = ?",
//            mapper,
//            id
//        )
//    }
//
//    override fun findAll(): MutableIterable<Todo> {
//        return jdbcTemplate.query(
//            "SELECT id, content FROM todos",
//            mapper
//        )
//    }
//
//    override fun findById(id: UUID): Optional<Todo> {
//        //단일객체의 Return의 경우 QuertForObject 사용
//        return Optional.ofNullable(jdbcTemplate.queryForObject(
//            "SELECT id, content " + "FROM todos WHERE id = ?",
//            mapper,
//            id
//        ))
//    }
//
//    override fun deleteAll() {
//        jdbcTemplate.query(
//            "DELETE FROM todos",
//        mapper
//        )
//    }
//}