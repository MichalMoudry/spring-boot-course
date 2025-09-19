package com.example.books.transport

import com.example.books.database.entity.Book
import com.example.books.transport.model.BookCreateRequest
import com.example.books.transport.model.BookResponse
import com.example.books.transport.model.BookUpdateRequest
import groovy.transform.CompileStatic
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api/books')
@CompileStatic
class BooksController {
    private static final List<Book> books = [
            new Book(1, 'title 1', 'author 1', 'science', 4.6f),
            new Book(2, 'title 2', 'author 2', 'history', 4.4f)
    ]

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
    Optional<BookResponse> getBook(
            @PathVariable('title') @Size(min = 3, max = 80) String title) {
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
    @ResponseStatus(HttpStatus.CREATED)
    void createBook(@Valid @RequestBody BookCreateRequest request) {
        boolean isNewBook = books.stream()
            .noneMatch {book -> book.title.equalsIgnoreCase(request.title)}
        if (isNewBook) {
            books.add(new Book(request.title, request.author, request.category, 0))
        }
    }

    @PutMapping('/{title}')
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateBook(
            @PathVariable('title') String title,
            @Valid @RequestBody BookUpdateRequest request) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equalsIgnoreCase(title)) {
                books.set(i, new Book(
                        request.title,
                        request.author,
                        request.category,
                        request.rating
                ))
                return
            }
        }
    }

    @DeleteMapping('/{id}')
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(@PathVariable('title') long id) {
        books.removeIf {book -> (book.id == id)}
    }
}
