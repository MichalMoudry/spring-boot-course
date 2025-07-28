package com.example.aop.aspects

import com.example.aop.model.Account
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

import java.time.Duration
import java.time.Instant

@Aspect
@Component
class DemoLoggingAspect {
    // @Before('execution(void com.example.aop.database.IAccountDao.addAccount())')
    /*@Before('execution(void addAccount())')
    static void beforeAddAccount() {
        println('====> Executing @Before on addAccount()')
    }

    @Before('addMethod()')
    static void pointCut() {
        println('// Executing point cut @Before')
    }

    @Pointcut('execution(* add*(..))')
    private void addMethod() { }*/

    @AfterReturning(
            pointcut = 'execution(* com.example.aop.database.IAccountDao.findAccounts(..))',
            returning = 'result'
    )
    static void logFindAccountsResponse(
            JoinPoint joinPoint,
            List<Account> result) {
        int count = result.size()
        println("Returned $count accounts")
    }

    @Around('execution(* com.example.aop.service.*.getFortune(..))')
    static Object aroundGetFortune(
            ProceedingJoinPoint joinPoint) throws Throwable {
        String methodSig = joinPoint.getSignature().toShortString()
        println("| Method: $methodSig")

        Instant start = Instant.now()
        def result = joinPoint.proceed()
        Duration duration = Duration.between(start, Instant.now())
        println("| Duration: ${duration.toMillis() / 1000}s")

        result
    }
}
