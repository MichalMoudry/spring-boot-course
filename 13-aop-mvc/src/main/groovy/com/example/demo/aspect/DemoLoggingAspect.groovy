package com.example.demo.aspect

import groovy.transform.CompileStatic
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

import java.util.logging.Logger

@Aspect
@Component
@CompileStatic
class DemoLoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName())

    @Pointcut('execution(* com.example.demo.transport.*.*(..))')
    static void forTransportPackage() { }

    @Before('forTransportPackage()')
    void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString()
        logger.info("| in @Before: calling $method")

        Object[] args = joinPoint.args
        if (args.size() > 0) {
            for (def arg in args) {
                logger.info("| argument: $arg")
            }
        }
    }

    @AfterReturning(pointcut = 'forTransportPackage()', returning = 'result')
    void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.signature.toShortString()
        logger.info("| in @AfterReturning: from $method")

        logger.info("| result: $result")
    }
}
