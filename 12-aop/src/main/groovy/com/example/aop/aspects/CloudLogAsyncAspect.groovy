package com.example.aop.aspects

import groovy.transform.CompileStatic
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
@CompileStatic
class CloudLogAsyncAspect {
    // @Before('SharedPointcuts.forDatabasePackage()')
    static void log(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs()
        for (def arg in args) {
            println("\tArg: $arg")
        }
        println('|> Log')
    }
}
