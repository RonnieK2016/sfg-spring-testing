package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LaurelWordProducer implements WordProducer {
    @Override
    public String getWord() {
        return "Laurel";
    }
}
