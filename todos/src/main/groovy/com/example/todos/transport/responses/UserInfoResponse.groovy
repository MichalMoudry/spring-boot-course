package com.example.todos.transport.responses

import com.example.todos.database.entity.Authority
import com.example.todos.database.entity.User
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
final class UserInfoResponse {
    @JsonProperty('id')
    private long id

    @JsonProperty('full_name')
    private String fullName

    @JsonProperty('email')
    private String email

    @JsonProperty('authorities')
    private List<Authority> authorities

    UserInfoResponse() {}

    UserInfoResponse(User user) {
        id = user.id
        fullName = "${user.firstName} ${user.lastName}"
        email = user.email
        authorities = user.authorities
                .stream()
                .map {i -> (Authority)i}
                .toList()
    }

    long getId() { id }

    String getFullName() { fullName }

    String getEmail() { email }

    List<Authority> getAuthorities() { authorities }
}
