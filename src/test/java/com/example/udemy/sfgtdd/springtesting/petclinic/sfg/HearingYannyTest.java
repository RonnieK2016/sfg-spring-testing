package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BaseConfig.class, YannyConfig.class})
public class HearingYannyTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void testWhatYouHear() {
        String word = hearingInterpreter.whatIheard();

        assertEquals("Yanny", word);
    }

}
