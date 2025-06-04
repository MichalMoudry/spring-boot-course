package com.springboot.demo.app

import groovy.transform.CompileStatic
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Primary
@Component
@CompileStatic
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
final class CricketCoach implements Coach {
    @Override
    String getDailyWorkout() {
        'Practice fast bowling for 15 minutes'
    }
}
