package com.springboot.demo.app

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@Component
@CompileStatic
final class BaseballCoach implements Coach {
    @Override
    String getDailyWorkout() {
        'Baseball coach string'
    }
}
