package com.springboot.demo.app.transport

import com.springboot.demo.app.Coach
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
final class DemoController {
    final Coach coach

    @Autowired
    DemoController(
            @Qualifier('aquatic') Coach coach) {
        this.coach = coach
    }

    /*@Autowired
    void setCoach(Coach coach) {
        this.coach = coach
    }*/

    @GetMapping('/workout')
    String getText() {
        coach.dailyWorkout
    }
}
