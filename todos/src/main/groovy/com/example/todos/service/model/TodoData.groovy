package com.example.todos.service.model

import com.example.todos.database.entity.Todo
import groovy.transform.CompileStatic

@CompileStatic
final class TodoData {
    long id
    String title
    String description
    int priority
    boolean isComplete

    TodoData(
            long id,
            String title,
            String description,
            int priority,
            boolean isComplete) {
        this.id = id
        this.title = title
        this.description = description
        this.priority = priority
        this.isComplete = isComplete
    }

    TodoData(Todo todo) {
        this.id = todo.id
        this.title = todo.title
        this.description = todo.description
        this.priority = todo.priority
        this.isComplete = todo.isComplete
    }
}
