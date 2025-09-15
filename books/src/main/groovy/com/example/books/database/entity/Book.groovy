package com.example.books.database.entity

import groovy.transform.CompileStatic

@CompileStatic
class Book {
    private String title
    private String author
    private String category

    Book(String title, String author, String category) {
        this.title = title
        this.author = author
        this.category = category
    }

    String getTitle() { title }

    void setTitle(String title) {
        this.title = title
    }

    String getAuthor() { author }

    void setAuthor(String author) {
        this.author = author
    }

    String getCategory() { category }

    void setCategory(String category) {
        this.category = category
    }
}
