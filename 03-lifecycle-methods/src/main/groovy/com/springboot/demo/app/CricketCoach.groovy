package com.springboot.demo.app

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@Component
@CompileStatic
final class CricketCoach implements Coach {
    CricketCoach() {
        println("In ctor(): ${getClass().getSimpleName()}")
    }

    @Override
    String getDailyWorkout() {
        'Practice fast bowling for 15 minutes'
    }
}
