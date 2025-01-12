package com.game.wordguess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WordGuessApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordGuessApplication.class, args);
    }

}
