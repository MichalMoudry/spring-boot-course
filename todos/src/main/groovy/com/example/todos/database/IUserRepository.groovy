package com.example.todos.database

import com.example.todos.database.entity.User
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email)

    @Query('SELECT COUNT(u) FROM User u JOIN u.authorities a where a.authority = "ROLE_ADMIN"')
    long countAdminUsers()
}
