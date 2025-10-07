package com.example.todos.transport.requests

import com.example.todos.service.model.TodoCreationData
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@CompileStatic
final class TodoRequest {
    @NotEmpty(message = 'Title is mandatory')
    @Size(min = 3, max = 58, message = 'Title must be at least 3 characters long')
    @JsonProperty('title')
    String title

    @NotEmpty(message = 'Description is mandatory')
    @Size(min = 3, max = 58, message = 'Description must be at least 3 characters long')
    @JsonProperty('description')
    String description

    @Min(1)
    @Max(5)
    @JsonProperty('priority')
    int priority

    TodoCreationData toServiceModel() {
        new TodoCreationData(title, description, priority)
    }
}
