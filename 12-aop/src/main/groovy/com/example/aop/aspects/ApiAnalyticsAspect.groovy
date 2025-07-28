package com.example.aop.aspects

import groovy.transform.CompileStatic
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
@CompileStatic
class ApiAnalyticsAspect {
    // @Before('SharedPointcuts.forDatabasePackage()')
    static void perform() {
        println('|> Performing API analytics')
    }
}
