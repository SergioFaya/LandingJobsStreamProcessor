package com.stream;

import com.stream.entity.JobOffer;
import com.stream.entity.LandingJobsJob;
import com.stream.serde.JobOfferSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

import static com.stream.processor.Processor.createJobOffer;

@SpringBootApplication
public class LandingJobsStreamProcessor {
    public static void main(String[] args) {
        SpringApplication.run(LandingJobsStreamProcessor.class, args);
    }

    @Bean
    public Consumer<KStream<String, LandingJobsJob>> process() {

        return input -> input
                .map((key, value) -> new KeyValue<String, JobOffer>(value.getId() + "", createJobOffer(value)))
                .toTable(Named.as("JOBS_STORAGE2"),
                        Materialized.<String, JobOffer, KeyValueStore<Bytes, byte[]>>as(
                                "JOBS_STORAGE2")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(new JobOfferSerde()));

    }
}

