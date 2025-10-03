package com.example.todos.service

import com.example.todos.database.IUserRepository
import com.example.todos.database.entity.Authority
import com.example.todos.database.entity.User
import com.example.todos.service.model.AuthData
import com.example.todos.service.model.AuthResult
import com.example.todos.service.model.UserRegisterData
import groovy.transform.CompileStatic
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@CompileStatic
class AuthService implements IAuthService {
    private final IUserRepository userRepository
    private final PasswordEncoder passwordEncoder
    private final AuthenticationManager authManager
    private final IJwtService jwtService

    AuthService(
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authManager,
            IJwtService jwtService) {
        this.userRepository = userRepository
        this.passwordEncoder = passwordEncoder
        this.authManager = authManager
        this.jwtService = jwtService
    }

    @Override
    @Transactional
    void register(UserRegisterData data) throws Exception {
        def isEmailTaken = isEmailRegistered(data.email())
        if (isEmailTaken) {
            throw new Exception('Email is already taken')
        }

        User user = buildNewUser(data)
        userRepository.save(user)
    }

    @Override
    @Transactional(readOnly = true)
    AuthResult login(AuthData requestData) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestData.email(),
                        requestData.password()
                )
        )
        User user = userRepository
                .findByEmail(requestData.email())
                .orElseThrow(() -> new IllegalArgumentException('Invalid email or password'))
        String jwtToken = jwtService.generateToken([:], user)

        new AuthResult(jwtToken)
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

    private boolean isEmailRegistered(String email) {
        userRepository.findByEmail(email).isPresent()
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
