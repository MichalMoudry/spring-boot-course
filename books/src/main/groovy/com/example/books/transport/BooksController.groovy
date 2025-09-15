package com.example.books.transport

import com.example.books.database.entity.Book
import com.example.books.transport.model.BookResponse
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CompileStatic
class BooksController {
    private static final List<Book> books = [
            new Book('title 1', 'author 1', 'science'),
            new Book('title 2', 'author 2', 'history')
    ]

    @GetMapping('/api/books')
    List<BookResponse> getBooks() {
        books.stream().map {
            Book i -> new BookResponse(i.title, i.author, i.category)
        }.toList()
    }
}
