package com.example.todos.transport.requests

import com.example.todos.service.model.NewPasswordData
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@CompileStatic
final class PasswordUpdateRequest {
    @NotEmpty(message = 'Old password is mandatory')
    @Size(min = 7, max = 256, message = 'Old password must be at least 7 characters long')
    @JsonProperty('old_pass')
    String oldPassword

    @NotEmpty(message = 'New password is mandatory')
    @Size(min = 7, max = 256, message = 'New password must be at least 7 characters long')
    @JsonProperty('new_pass')
    String newPassword

    @NotEmpty(message = 'Confirmed password is mandatory')
    @Size(min = 7, max = 256, message = 'Confirmed password must be at least 7 characters long')
    @JsonProperty('new_pass_verification')
    String newPasswordVerification

    PasswordUpdateRequest() { }

    PasswordUpdateRequest(
            String oldPassword,
            String newPassword,
            String newPasswordVerification) {
        this.oldPassword = oldPassword
        this.newPassword = newPassword
        this.newPasswordVerification = newPasswordVerification
    }

    NewPasswordData toServiceModel() {
        new NewPasswordData(oldPassword, newPassword, newPasswordVerification)
    }
}
