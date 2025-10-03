package com.example.todos.service

import com.example.todos.database.IUserRepository
import com.example.todos.database.entity.User
import groovy.transform.CompileStatic
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.nio.file.AccessDeniedException

@Service
@CompileStatic
class UserService implements IUserService {
    private final IUserRepository userRepository

    UserService(IUserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    @Transactional(readOnly = true)
    User getUserInfo() {
        Authentication auth = SecurityContextHolder.context.authentication
        if (auth == null || !auth.isAuthenticated() || auth.principal == 'anonymousUser') {
            throw new AccessDeniedException('Authentication is required')
        }

        (User)auth.principal
    }
}
