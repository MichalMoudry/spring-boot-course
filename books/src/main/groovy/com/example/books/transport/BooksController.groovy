package com.example.books.transport

import com.example.books.database.entity.Book
import com.example.books.transport.errors.BookNotFoundErr
import com.example.books.transport.model.BookCreateRequest
import com.example.books.transport.model.BookResponse
import com.example.books.transport.model.BookUpdateRequest
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = 'Books REST API endpoint', description = 'Operations related to books')
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
    @Operation(
            summary = 'Get all books',
            description = 'Retrieve a list of all available books')
    List<BookResponse> getBooks(
            @Parameter(description = 'Optional query parameter')
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
    @Operation(
            summary = 'Get a book by title',
            description = 'Retrieve a specific book by title')
    Optional<BookResponse> getBook(
            @PathVariable('title') @Size(min = 3, max = 80) String title) {
        for (Book book in books) {
            if (book.title == title) {
                return Optional<BookResponse>.of(
                        new BookResponse(book.title, book.author, book.category)
                )
            }
        }

        throw new BookNotFoundErr("Book not found - $title")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = 'Create a new book',
            description = 'Add a new book to the list')
    void createBook(@Valid @RequestBody BookCreateRequest request) {
        boolean isNewBook = books.stream()
            .noneMatch {book -> book.title.equalsIgnoreCase(request.title)}
        if (isNewBook) {
            books.add(new Book(request.title, request.author, request.category, 0))
        }
    }

    @PutMapping('/{title}')
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = 'Update a book',
            description = 'Updated the details of an existing book')
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
    @Operation(
            summary = 'Delete a book',
            description = 'Remove a book from the list')
    void deleteBook(@PathVariable('title') long id) {
        books.removeIf {book -> (book.id == id)}
    }
}
