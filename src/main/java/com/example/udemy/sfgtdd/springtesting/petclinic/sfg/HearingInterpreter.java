package com.example.udemy.sfgtdd.springtesting.petclinic.sfg;

import org.springframework.stereotype.Service;

@Service
public class HearingInterpreter {

    private WordProducer wordProducer;

    public HearingInterpreter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String whatIheard(){
        String word = wordProducer.getWord();

        System.out.println(word);

        return word;
    }
}
