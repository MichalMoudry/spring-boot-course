package com.example.todos.transport.filters

import com.example.todos.service.IJwtService
import groovy.transform.CompileStatic
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Lazy
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@CompileStatic
class JwtAuthFilter extends OncePerRequestFilter {
    private final IJwtService jwtService
    private final UserDetailsService userDetailsService

    JwtAuthFilter(
            IJwtService jwtService,
            @Lazy UserDetailsService userDetailsService) {
        this.jwtService = jwtService
        this.userDetailsService = userDetailsService
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader('Authorization')
        if (authHeader == null || !authHeader.startsWith('Bearer ')) {
            filterChain.doFilter(request, response)
            return
        }

        final String jwt = authHeader.substring(7)
        final String email = jwtService.extractUserName(jwt)
        if (email != null && SecurityContextHolder.context.authentication == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email)
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.authorities
                        )
                token.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                )
                SecurityContextHolder.context.setAuthentication(token)
            }
        }
        filterChain.doFilter(request, response)
    }
}
