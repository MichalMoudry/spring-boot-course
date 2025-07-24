package com.example.aop.database

import com.example.aop.model.Account
import groovy.transform.CompileStatic
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
class AccountDao implements IAccountDao {
    @Override
    void addAccount(Account account) {
        println("${getClass()}: adding an account $account")
    }
}
