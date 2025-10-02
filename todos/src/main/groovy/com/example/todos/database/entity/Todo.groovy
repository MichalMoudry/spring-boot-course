package com.example.todos.database.entity

import groovy.transform.CompileStatic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = 'todos')
@CompileStatic
class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'id', nullable = false)
    private long id

    @Column(name = 'title', nullable = false)
    private String title

    @Column(name = 'description', nullable = false)
    private String description

    @Column(name = 'priority', nullable = false)
    private int priority

    @Column(name = 'is_complete', nullable = false)
    private boolean isComplete

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = 'owner_id', nullable = false)
    private User owner

    Todo() { }

    Todo(String title, String description, int priority, boolean isComplete) {
        this.title = title
        this.description = description
        this.priority = priority
        this.isComplete = isComplete
    }

    long getId() { id }

    void setId(long id) {
        this.id = id
    }

    String getTitle() { title }

    void setTitle(String title) {
        this.title = title
    }

    String getDescription() { description }

    void setDescription(String description) {
        this.description = description
    }

    int getPriority() { priority }

    void setPriority(int priority) {
        this.priority = priority
    }

    boolean getIsComplete() { isComplete }

    void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete
    }
}
