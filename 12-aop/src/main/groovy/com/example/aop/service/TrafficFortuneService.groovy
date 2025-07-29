package com.example.aop.service

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service

import java.util.concurrent.TimeUnit

@Service
@CompileStatic
class TrafficFortuneService implements ITrafficFortuneService {
    @Override
    String getFortune() {
        try {
            TimeUnit.SECONDS.sleep(5)
        } catch (InterruptedException e) {
            throw new RuntimeException(e)
        }
        'Expect heavy traffic this morning'
    }

    @Override
    String getFortuneWithErr() {
        throw new RuntimeException('Major accident')
    }
}
