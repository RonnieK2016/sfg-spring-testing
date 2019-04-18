package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = {HearingInterpreterTestInnerClassConfig.TestConfig.class})
@ActiveProfiles(profiles = "inner-class")
class HearingInterpreterTestInnerClassConfig {

    @Configuration
    @ComponentScan(basePackages = "com.example.udemy.sfgtdd.springtesting.petclinic.sfg")
    static class TestConfig {
    }

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Test
    void whatIheard() {
        String word = hearingInterpreter.whatIheard();
        assertEquals("Laurel", word);
    }
}