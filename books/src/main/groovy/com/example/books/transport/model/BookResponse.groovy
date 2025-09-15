package com.example.books.transport.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
final class BookResponse {
    @JsonProperty('title')
    String title
    @JsonProperty('author')
    String author
    @JsonProperty('category')
    String category

    BookResponse() { }

    BookResponse(String title, String author, String category) {
        this.title = title
        this.author = author
        this.category = category
    }
}
