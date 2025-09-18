package com.example.books.transport

import com.example.books.database.entity.Book
import com.example.books.transport.model.BookCreateRequest
import com.example.books.transport.model.BookResponse
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api/books')
@CompileStatic
class BooksController {
    private static final List<Book> books = [
            new Book('title 1', 'author 1', 'science'),
            new Book('title 2', 'author 2', 'history')
    ]

    @GetMapping
    List<BookResponse> getBooks(
            @RequestParam(name = 'category', required = false) String category) {
        if (category == null) {
            ArrayList<BookResponse> response = new ArrayList<BookResponse>(
                    books.size()
            )
            for (Book book in books) {
                response.add(
                        new BookResponse(book.title, book.author, book.category)
                )
            }
            return response
        }
        return books.stream()
                .filter {book -> book.category.equalsIgnoreCase(category)}
                .map {book -> new BookResponse(book.title, book.author, book.category)}
                .toList()
    }

    @GetMapping('/{title}')
    Optional<BookResponse> getBook(@PathVariable('title') String title) {
        if (title == null || title == "") {
            return Optional<BookResponse>.empty()
        }

        for (Book book in books) {
            if (book.title == title) {
                return Optional<BookResponse>.of(
                        new BookResponse(book.title, book.author, book.category)
                )
            }
        }

        return Optional<BookResponse>.empty()
    }

    @PostMapping
    void createBook(@RequestBody BookCreateRequest request) {
        boolean isNewBook = books.stream()
            .noneMatch {book -> book.title.equalsIgnoreCase(request.title)}
        if (isNewBook) {
            books.add(new Book(request.title, request.author, request.category))
        }
    }

    @PutMapping('/{title}')
    void updateBook(
            @PathVariable('title') String title,
            @RequestBody BookCreateRequest request) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equalsIgnoreCase(title)) {
                books.set(i, new Book(request.title, request.author, request.category))
                return
            }
        }
    }

    @DeleteMapping('/{title}')
    void deleteBook(@PathVariable('title') String title) {
        books.removeIf {book -> book.title.equalsIgnoreCase(title)}
    }
}
