package com.springboot.demo.app.transport

import com.springboot.demo.app.Coach
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
final class DemoController {
    final Coach coach
    final Coach anotherCoach

    @Autowired
    DemoController(
            /*@Qualifier('baseballCoach') */Coach coach,
            Coach anotherCoach) {
        this.coach = coach
        this.anotherCoach = anotherCoach
    }

    /*@Autowired
    void setCoach(Coach coach) {
        this.coach = coach
    }*/

    @GetMapping('/workout')
    String getText() {
        coach.dailyWorkout
    }

    @GetMapping('/check')
    String checkScopes() {
        "Comparing bean scopes: ${coach == anotherCoach}"
    }
}
