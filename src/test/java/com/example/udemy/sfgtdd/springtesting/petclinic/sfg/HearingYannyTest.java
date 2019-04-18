package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {BaseConfig.class, YannyConfig.class})
public class HearingYannyTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void testWhatYouHear() {
        String word = hearingInterpreter.whatIheard();

        assertEquals("Yanny", word);
    }

}
