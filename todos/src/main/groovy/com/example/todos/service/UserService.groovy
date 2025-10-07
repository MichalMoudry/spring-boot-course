package com.example.todos.service

import com.example.todos.database.IUserRepository
import com.example.todos.database.entity.User
import com.example.todos.service.model.NewPasswordData
import com.example.todos.service.util.IFindAuthenticatedUser
import groovy.transform.CompileStatic
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
@CompileStatic
class UserService implements IUserService {
    private final IUserRepository userRepository
    private final IFindAuthenticatedUser authenticatedUser
    private final PasswordEncoder passwordEncoder

    UserService(
            IUserRepository userRepository,
            IFindAuthenticatedUser authenticatedUser,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository
        this.authenticatedUser = authenticatedUser
        this.passwordEncoder = passwordEncoder
    }

    @Override
    @Transactional(readOnly = true)
    User getUserInfo() { authenticatedUser.authenticatedUser }

    @Override
    @Transactional
    void deleteUser() {
        User user = getUserInfo()
        boolean isLastAdmin = isLastAdmin(user)
        if (isLastAdmin) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    'Admin can\'t delete itself'
            )
        }

        userRepository.delete(user)
    }

    @Override
    @Transactional
    void updatePassword(NewPasswordData data) {
        if (data.newPassword() != data.passwordConfirmation()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password confirmation doesn't match"
            )
        }
        User user = authenticatedUser.getAuthenticatedUser()
        if (!isOldPasswordCorrect(user.password, data.oldPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    'Current password is incorrect'
            )
        }
        if (data.oldPassword() == data.newPassword()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    'Old and new password must be different'
            )
        }

        user.password = data.newPassword()
        userRepository.save(user)
    }

    private boolean isLastAdmin(User user) {
        boolean isAdmin = user.getAuthorities()
                .stream()
                .anyMatch {i -> ("ROLE_ADMIN" == i.authority)}
        if (isAdmin) {
            long adminCount = userRepository.countAdminUsers()
            return adminCount <= 0
        }
        return false
    }

    private boolean isOldPasswordCorrect(String currentPass, String oldPass) {
        passwordEncoder.matches(oldPass, currentPass)
    }
}
