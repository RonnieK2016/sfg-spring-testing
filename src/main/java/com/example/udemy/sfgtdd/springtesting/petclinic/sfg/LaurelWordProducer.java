package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"laurel-profile","package-scan","inner-class"})
public class LaurelWordProducer implements WordProducer {
    @Override
    public String getWord() {
        return "Laurel";
    }
}
