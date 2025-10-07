package com.example.todos.transport.responses

import com.example.todos.service.model.TodoData
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
final class TodoResponse {
    @JsonProperty('id')
    private long id

    @JsonProperty('title')
    private String title

    @JsonProperty('description')
    private String description

    @JsonProperty('prio')
    private int priority

    @JsonProperty('is_complete')
    private boolean isComplete

    TodoResponse(long id, String title, String description, int priority, boolean isComplete) {
        this.id = id
        this.title = title
        this.description = description
        this.priority = priority
        this.isComplete = isComplete
    }

    TodoResponse(TodoData serviceModel) {
        id = serviceModel.id
        title = serviceModel.title
        description = serviceModel.description
        priority = serviceModel.priority
        isComplete = serviceModel.isComplete
    }

    long getId() { id }

    String getTitle() { title }

    String getDescription() { description }

    int getPriority() { priority }

    boolean getIsComplete() { isComplete }
}
