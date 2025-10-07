package com.example.todos.service.model

import groovy.transform.CompileStatic

@CompileStatic
record TodoCreationData(
        String title,
        String description,
        int priority
) {}