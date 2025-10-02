package com.example.todos.service

import com.example.todos.database.IUserRepository
import com.example.todos.database.entity.Authority
import com.example.todos.database.entity.User
import com.example.todos.service.model.UserRegisterData
import groovy.transform.CompileStatic
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@CompileStatic
class AuthService implements IAuthService {
    private final IUserRepository userRepository
    private final PasswordEncoder passwordEncoder

    AuthService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository
        this.passwordEncoder = passwordEncoder
    }

    @Override
    @Transactional
    void register(UserRegisterData data) throws Exception {
        User user = buildNewUser(data)
        assignAuthorities(data)
        userRepository.save(user)
    }

    private User buildNewUser(UserRegisterData data) {
        User user = new User(
                data.firstName(),
                data.lastName(),
                data.email(),
                passwordEncoder.encode(data.password())
        )
        user.authorities = initialAuthority()
        user
    }

    private List<Authority> initialAuthority() {
        def isFirstUser = userRepository.count() == 0
        List<Authority> authorities = new ArrayList<>(1)
        authorities.add(new Authority('ROLE_EMPLOYEE'))
        if (isFirstUser) {
            authorities.add(new Authority('ROLE_ADMIN'))
        }

        authorities
    }
}
