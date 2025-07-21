package com.example.demo.entity

import groovy.transform.CompileStatic
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = 'student')
@CompileStatic
class Student {
}
