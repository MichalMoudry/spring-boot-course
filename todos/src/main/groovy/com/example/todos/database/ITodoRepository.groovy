package com.example.todos.database

import com.example.todos.database.entity.Todo
import com.example.todos.database.entity.User
import groovy.transform.CompileStatic
import org.springframework.data.repository.CrudRepository

@CompileStatic
interface ITodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByOwner(User owner)
}
