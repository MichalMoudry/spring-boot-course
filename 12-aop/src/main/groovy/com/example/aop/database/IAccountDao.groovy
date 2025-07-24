package com.example.aop.database

import com.example.aop.model.Account
import groovy.transform.CompileStatic

@CompileStatic
interface IAccountDao {
    void addAccount(Account account)
}