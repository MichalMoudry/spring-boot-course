package com.example.books.transport.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
final class BookCreateRequest {
    @JsonProperty('title')
    String title
    @JsonProperty('author')
    String author
    @JsonProperty('category')
    String category
}
