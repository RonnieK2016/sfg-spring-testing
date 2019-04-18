package com.example.udemy.sfgtdd.springtesting.petclinic.config;

import com.example.udemy.sfgtdd.springtesting.petclinic.sfg.LaurelWordProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("base-test")
public class LaurelConfig {

    @Bean
    LaurelWordProducer laurelWordProducer() {
        return new LaurelWordProducer();
    }
}
