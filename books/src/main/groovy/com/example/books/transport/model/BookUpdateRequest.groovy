package com.example.books.transport.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

@CompileStatic
final class BookUpdateRequest {
    @Size(min = 3, max = 80)
    @JsonProperty('title')
    String title

    @Size(min = 2, max = 80)
    @JsonProperty('author')
    String author

    @Size(min = 3, max = 50)
    @JsonProperty('category')
    String category

    @Min(value = 1)
    @Max(value = 5)
    @JsonProperty('rating')
    float rating
}
