package com.example.todos.database.entity

import groovy.transform.CompileStatic
import jakarta.persistence.Embeddable
import org.springframework.security.core.GrantedAuthority

@Embeddable
@CompileStatic
class Authority implements GrantedAuthority {
    private String authority

    Authority() { }

    Authority(String authority) {
        this.authority = authority
    }

    @Override
    String getAuthority() {
        return null
    }
}
