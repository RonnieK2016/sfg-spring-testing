package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = PropertiesTest.TestConfig.class)
@TestPropertySource("classpath:yanny.properties")
@ActiveProfiles(profiles = "properties-profile", inheritProfiles = false)
public class PropertiesTest {

    @Configuration
    @ComponentScan(basePackages = "com.example.udemy.sfgtdd.springtesting.petclinic.sfg")
    static class TestConfig {
    }

    @Autowired
    private HearingInterpreter hearingInterpreter;

    @Test
    void whatIheard() {
        String word = hearingInterpreter.whatIheard();

        assertEquals("YaNNy", word);
    }
}