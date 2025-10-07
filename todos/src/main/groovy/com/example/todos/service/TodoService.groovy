package com.example.todos.service

import com.example.todos.database.ITodoRepository
import com.example.todos.database.entity.Todo
import com.example.todos.database.entity.User
import com.example.todos.service.model.TodoCreationData
import com.example.todos.service.model.TodoData
import com.example.todos.service.util.IFindAuthenticatedUser
import groovy.transform.CompileStatic
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@CompileStatic
class TodoService implements ITodoService {
    private final ITodoRepository todoRepository
    private final IFindAuthenticatedUser findAuthenticatedUser

    TodoService(
            ITodoRepository todoRepository,
            IFindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository
        this.findAuthenticatedUser = findAuthenticatedUser
    }

    @Transactional
    @Override
    TodoData createTodo(TodoCreationData data) {
        User currentUser = findAuthenticatedUser.authenticatedUser
        Todo todo = new Todo(
                data.title(),
                data.description(),
                data.priority(),
                false,
                currentUser
        )
        todoRepository.save(todo)

        new TodoData(
                todo.id,
                todo.title,
                todo.description,
                todo.priority,
                todo.isComplete
        )
    }

    @Override
    List<TodoData> getTodos() {
        User currentUser = findAuthenticatedUser.authenticatedUser
        List<Todo> todos = todoRepository.findByOwner(currentUser)

        todos.stream().map {Todo i -> new TodoData(i)}.toList()
    }
}
