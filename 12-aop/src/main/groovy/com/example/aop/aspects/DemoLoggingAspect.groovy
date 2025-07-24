package com.example.aop.aspects

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class DemoLoggingAspect {
    // @Before('execution(void com.example.aop.database.IAccountDao.addAccount())')
    @Before('execution(void addAccount())')
    static void beforeAddAccount() {
        println('====> Executing @Before on addAccount()')
    }

    @Before('execution(* add*(..))')
    static void pointCut() {
        println('// Executing point cut @Before')
    }
}
