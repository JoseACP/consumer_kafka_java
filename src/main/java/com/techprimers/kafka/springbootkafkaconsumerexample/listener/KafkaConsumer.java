package com.techprimers.kafka.springbootkafkaconsumerexample.listener;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = {"player-data-ftmx", "player-login-records-ftmx", "player-beneficiary-ftmx"}, 
                   group = "test-group")
    public void consumePlayerData(String message) {
        System.out.println("Consumed Player Data: " + message);
    }

    @KafkaListener(topics = {"casino-bets-ftmx"}, group = "test-group")
    public void consumeCasinoBets(String message) {
        System.out.println("Consumed Casino Bets: " + message);
    }

    @KafkaListener(topics = {"sport-bets-ftmx"}, group = "test-group")
    public void consumeSportBets(String message) {
        System.out.println("Consumed Sport Bets: " + message);
    }
}
