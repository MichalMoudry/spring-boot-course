package com.example.aop.model

import groovy.transform.CompileStatic

@CompileStatic
final class Account {
    private String name
    private String level

    Account() { }

    Account(String name, String level) {
        this.name = name
        this.level = level
    }

    String getName() {
        name
    }

    void setName(String name) {
        this.name = name
    }

    String getLevel() {
        level
    }

    void setLevel(String level) {
        this.level = level
    }

    @Override
    String toString() {
        "Account[name='$name', level='$level']"
    }
}
