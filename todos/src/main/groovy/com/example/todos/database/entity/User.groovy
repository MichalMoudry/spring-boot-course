package com.example.todos.database.entity

import groovy.transform.CompileStatic
import jakarta.persistence.CascadeType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.time.OffsetDateTime

@Entity
@Table(name = 'users')
@CompileStatic
class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = 'id', nullable = false)
    private long id

    @Column(name = 'first_name', nullable = false)
    private String firstName

    @Column(name = 'last_name', nullable = false)
    private String lastName

    @Column(name = 'email', unique = true, length = 100, nullable = false)
    private String email

    @Column(name = 'password', nullable = false)
    private String password

    @CreationTimestamp
    @Column(name = 'created_at', nullable = false)
    private OffsetDateTime createdAt

    @UpdateTimestamp
    @Column(name = 'updated_at', nullable = false)
    private OffsetDateTime updatedAt

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = 'user_authorities',
            joinColumns = @JoinColumn(name = 'user_id'))
    private List<Authority> authorities

    @OneToMany(mappedBy = 'owner', cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos

    User() { }

    User(String firstName, String lastName, String email, String password) {
        // OffsetDateTime now = OffsetDateTime.now()
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() { authorities }

    void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities
    }

    @Override
    String getPassword() { password }

    @Override
    String getUsername() { email }

    @Override
    boolean isAccountNonExpired() {
        return super.isAccountNonExpired()
    }

    @Override
    boolean isAccountNonLocked() {
        return super.isAccountNonLocked()
    }

    @Override
    boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired()
    }

    @Override
    boolean isEnabled() { super.isEnabled() }

    long getId() { id }

    void setId(long id) {
        this.id = id
    }

    String getFirstName() { firstName }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() { lastName }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getEmail() { email }

    void setEmail(String email) {
        this.email = email
    }

    void setPassword(String password) {
        this.password = password
    }
}
