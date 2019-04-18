package com.example.udemy.sfgtdd.springtesting.petclinic.config;

import com.example.udemy.sfgtdd.springtesting.petclinic.sfg.HearingInterpreter;
import com.example.udemy.sfgtdd.springtesting.petclinic.sfg.WordProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("base-test")
public class BaseConfig {

    @Bean
    HearingInterpreter hearingInterpreter(WordProducer wordProducer) {
        return new HearingInterpreter(wordProducer);
    }
}
