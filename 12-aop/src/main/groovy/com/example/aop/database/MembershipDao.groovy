package com.example.aop.database

import groovy.transform.CompileStatic
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
class MembershipDao implements IMembershipDao {
    @Override
    boolean addMembership() {
        println("${getClass()}: adding a membership account")
        true
    }
}
