package com.example.todos.database

import com.example.todos.database.entity.User
import groovy.transform.CompileStatic
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email)
}