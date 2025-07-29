package com.example.demo.security

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@CompileStatic
class DemoSecurityConfig {
    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user1 = User.builder()
            .username('john')
            .password('{noop}test123')
            .roles('EMPLOYEE')
            .build()
        UserDetails user2 = User.builder()
                .username('mary')
                .password('{noop}test123')
                .roles('EMPLOYEE', 'MANAGER')
                .build()
        UserDetails user3 = User.builder()
                .username('susan')
                .password('{noop}test123')
                .roles('EMPLOYEE', 'MANAGER', 'ADMIN')
                .build()
        new InMemoryUserDetailsManager(user1, user2, user3)
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests { configurer ->
            configurer
                    .requestMatchers('/').hasRole('EMPLOYEE')
                    .requestMatchers('/leaders/**').hasRole('MANAGER')
                    .requestMatchers('/systems/**').hasRole('ADMIN')
                    .anyRequest()
                    .authenticated()
        }.formLogin { form ->
            form
                    .loginPage('/showMyLoginPage')
                    .loginProcessingUrl('/authenticateTheUser')
                    .permitAll()
        }.logout {
            logout -> logout.permitAll()
        }.exceptionHandling { config ->
            config.accessDeniedPage('/access-denied')
        }
        http.build()
    }
}
