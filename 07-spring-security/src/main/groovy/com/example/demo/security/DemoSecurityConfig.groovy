package com.example.demo.security

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain

import javax.sql.DataSource

@Configuration
@CompileStatic
class DemoSecurityConfig {
    /*@Bean
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
        new InMemoryUserDetailsManager(user1, user2)
    }*/

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource)
        /*manager.setUsersByUsernameQuery(
                'select user_id, pw, active from users where user_id=?'
        )*/
        manager
    }

    @Bean
    SecurityFilterChain roleFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests {config ->
            config
                    .requestMatchers(HttpMethod.GET, '/api/employees').hasRole('EMPLOYEE')
                    .requestMatchers(HttpMethod.GET, '/api/employees/**').hasRole('EMPLOYEE')
                    .requestMatchers(HttpMethod.POST, '/api/employees').hasRole('MANAGER')
                    .requestMatchers(HttpMethod.PUT, '/api/employees').hasRole('MANAGER')
                    .requestMatchers(HttpMethod.DELETE, '/api/employees/**').hasRole('MANAGER')
        }
        http.httpBasic(Customizer.withDefaults())
        http.csrf {csrf -> csrf.disable()}
        http.build()
    }
}
