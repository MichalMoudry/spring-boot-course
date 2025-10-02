package com.example.todos.config

import com.example.todos.database.IUserRepository
import com.example.todos.transport.filters.JwtAuthFilter
import groovy.transform.CompileStatic
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@CompileStatic
class SecurityConfig {
    private final IUserRepository userRepository
    private final JwtAuthFilter jwtAuthFilter

    SecurityConfig(IUserRepository userRepository, JwtAuthFilter jwtAuthFilter) {
        this.userRepository = userRepository
        this.jwtAuthFilter = jwtAuthFilter
    }

    @Bean
    UserDetailsService userDetailsService() {
        { String username -> userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException('User not found')) }
    }

    @Bean
    PasswordEncoder passwordEncoder() { new BCryptPasswordEncoder() }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        config.authenticationManager
    }

    @Bean
    AuthenticationEntryPoint authEntryPoint() {
        return (HttpServletRequest req, HttpServletResponse response, Exception err) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value())
            response.setContentType('application/json')
            response.setHeader('WWW-Authenticate', '')
            response.getWriter().write('{"error": "Unauthorized access"}')
        }
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests {config ->
            config.requestMatchers(
                    '/swagger-ui/**',
                    '/v3/api-docs/**',
                    '/swagger-resources/**',
                    '/webjars/**',
                    '/docs/**'
            ).permitAll()
        }

        http.csrf {csrf -> csrf.disable()}
        // TODO: fix
        /*http.exceptionHandling {
            errHandling -> errHandling.authenticationEntryPoint {
                authEntryPoint()
            }
        }*/
        http.sessionManagement {
            session -> session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
            )
        }
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter)

        http.build()
    }
}
