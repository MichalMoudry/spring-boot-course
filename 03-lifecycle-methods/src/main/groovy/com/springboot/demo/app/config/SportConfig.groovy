package com.springboot.demo.app.config

import com.springboot.demo.app.Coach
import com.springboot.demo.app.SwimCoach
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SportConfig {
    @Bean('aquatic')
    static Coach swimCoach() {
        new SwimCoach()
    }
}
