package com.example.aop.database

import groovy.transform.CompileStatic

@CompileStatic
interface IMembershipDao {
    boolean addMembership()
}