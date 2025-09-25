package com.example.employees.security

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain

import javax.sql.DataSource

@Configuration
@CompileStatic
class SecurityConfig {
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource)

        manager.setUsersByUsernameQuery(
                'select user_id, password, active from system_users where user_id=?'
        )
        manager.setAuthoritiesByUsernameQuery(
                'select user_id, role from roles where user_id=?'
        )

        manager
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests {config ->
            config
                    .requestMatchers('/swagger/swagger-ui/**', '/swagger/json/**', '/v3/api-docs/**', '/swagger-ui.html')
                        .permitAll()
                    .requestMatchers(HttpMethod.GET, '/api/employees').hasRole('EMPLOYEE')
                    .requestMatchers(HttpMethod.GET, '/api/employees/**').hasRole('EMPLOYEE')
                    .requestMatchers(HttpMethod.POST, '/api/employees').hasRole('MANAGER')
                    .requestMatchers(HttpMethod.PUT, '/api/employees').hasRole('MANAGER')
                    .requestMatchers(HttpMethod.DELETE, '/api/employees/**').hasRole('ADMIN')
                    .requestMatchers(HttpMethod.GET, '/h2-console/**').permitAll()
                    .requestMatchers(HttpMethod.POST, '/h2-console/**').permitAll()
        }

        http.httpBasic{customizer -> customizer.disable()}
        http.httpBasic(Customizer.withDefaults())

        http.csrf {csrf -> csrf.disable()}

        http.headers {
            headers -> headers.frameOptions {
                HeadersConfigurer.FrameOptionsConfig options -> options.disable()
            }
        }

        http.build()
    }
}
