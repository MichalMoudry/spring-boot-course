package com.example.books.database.entity

import groovy.transform.CompileStatic

@CompileStatic
class Book {
    private long id
    private String title
    private String author
    private String category
    private float rating

    Book(long id, String title, String author, String category, float rating) {
        this.id = id
        this.title = title
        this.author = author
        this.category = category
        this.rating = rating
    }

    Book(String title, String author, String category, float rating) {
        this.title = title
        this.author = author
        this.category = category
        this.rating = rating
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

    long getId() { id }

    void setId(long id) {
        this.id = id
    }

    float getRating() { rating }

    void setRating(float rating) {
        this.rating = rating
    }
}
