package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import com.example.udemy.sfgtdd.springtesting.petclinic.config.BaseConfig;
import com.example.udemy.sfgtdd.springtesting.petclinic.config.YannyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("base-test")
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
