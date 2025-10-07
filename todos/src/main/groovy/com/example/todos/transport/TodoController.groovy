package com.example.todos.transport

import com.example.todos.service.ITodoService
import com.example.todos.service.model.TodoData
import com.example.todos.transport.requests.TodoRequest
import com.example.todos.transport.responses.TodoResponse
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api/todos')
@Tag(name = 'Todo REST API', description = 'Operations for managing user todos')
@CompileStatic
class TodoController {
    private final ITodoService todoService

    TodoController(ITodoService todoService) {
        this.todoService = todoService
    }

    @Operation(
            summary = "Current user's todos",
            description = "Returns a list of current user's todos")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<TodoResponse> getTodos() {
        List<TodoData> todos = todoService.getTodos()
        todos.stream().map {TodoData i -> new TodoResponse(i)}.toList()
    }

    @Operation(
            summary = 'Create a todo user user',
            description = 'Create a todo for the signed in user')
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    TodoResponse createTodo(@Valid @RequestBody TodoRequest request) {
        TodoData result = todoService.createTodo(request.toServiceModel())
        new TodoResponse(result)
    }
}
