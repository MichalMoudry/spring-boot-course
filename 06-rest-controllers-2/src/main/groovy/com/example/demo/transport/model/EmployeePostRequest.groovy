package com.example.demo.transport.model

import com.example.demo.service.model.EmployeeDto
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
final class EmployeePostRequest {
    @JsonProperty('id')
    UUID id
    @JsonProperty('first_name')
    String firstName
    @JsonProperty('last_name')
    String lastName
    @JsonProperty('email')
    String email

    static EmployeePostRequest fromServiceModel(EmployeeDto dto) {
        EmployeePostRequest result = new EmployeePostRequest()
        result.setId(dto.getId())
        result.setFirstName(dto.getFirstName())
        result.setLastName(dto.getLastName())
        result.setEmail(dto.getEmail())
        result
    }
}
