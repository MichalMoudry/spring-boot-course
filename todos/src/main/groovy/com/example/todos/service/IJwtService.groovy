package com.example.todos.service

import groovy.transform.CompileStatic
import org.springframework.security.core.userdetails.UserDetails

@CompileStatic
interface IJwtService {
    String extractUserName(String token)
    boolean isTokenValid(String token, UserDetails userDetails)
    String generateToken(Map<String, Object> claims, UserDetails userDetails)
}