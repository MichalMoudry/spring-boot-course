package com.example.todos.service

import com.example.todos.service.model.TodoCreationData
import com.example.todos.service.model.TodoData
import groovy.transform.CompileStatic

@CompileStatic
interface ITodoService {
    TodoData createTodo(TodoCreationData data)
    List<TodoData> getTodos()
}
