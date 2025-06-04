package com.springboot.demo.app

import groovy.transform.CompileStatic

@CompileStatic
final class SwimCoach implements Coach  {
    SwimCoach() {
        println('In ctor' + this.getClass().getSimpleName())
    }

    @Override
    String getDailyWorkout() {
        'Swim a 1000 meters as a warm up'
    }
}
