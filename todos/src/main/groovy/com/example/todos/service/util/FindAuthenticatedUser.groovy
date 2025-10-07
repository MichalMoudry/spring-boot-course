package com.example.todos.service.util

import com.example.todos.database.entity.User
import groovy.transform.CompileStatic
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

import java.nio.file.AccessDeniedException

@Component
@CompileStatic
class FindAuthenticatedUser implements IFindAuthenticatedUser {
    @Override
    User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.context.authentication
        if (auth == null
                || !auth.isAuthenticated()
                || auth.principal == 'anonymousUser') {
            throw new AccessDeniedException('Authentication is required')
        }

        (User)auth.principal
    }
}
