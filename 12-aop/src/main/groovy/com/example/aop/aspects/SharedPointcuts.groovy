package com.example.aop.aspects

import groovy.transform.CompileStatic
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
@CompileStatic
class SharedPointcuts {
    @Pointcut('execution(* com.example.aop.database.*.*(..))')
    static void forDatabasePackage() { }

    @Pointcut('execution(* com.example.aop.database.*.get*(..))')
    static void forGetters() { }

    @Pointcut('execution(* com.example.aop.database.*.set*(..))')
    static void forSetters() { }
}
