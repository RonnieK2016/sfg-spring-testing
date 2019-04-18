package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class YannyWordProducer implements WordProducer {
    @Override
    public String getWord() {
        return "Yanny";
    }
}
