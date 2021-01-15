package com.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class LandingJobsStreamProcessor {
    public static void main(String[] args) {
        SpringApplication.run(LandingJobsStreamProcessor.class, args);
    }
}

