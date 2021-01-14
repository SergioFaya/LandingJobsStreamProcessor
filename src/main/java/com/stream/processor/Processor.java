package com.stream.processor;

import com.stream.entity.LandingJobsJob;
import com.stream.serdes.SerdesFactory;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Processor {

    // TODO: in the future deployment extract topics to env variable and get it through docker compose

    private static final String topicIn = "topic1";
    private static final String topicOut = "topic2";

    @Autowired
    public void process(final StreamsBuilder builder) {
        KStream<String, LandingJobsJob> textLines = builder.stream(topicIn, Consumed.with(Serdes.String(),
                SerdesFactory.landingJobsJobSerde()));

        textLines
                .mapValues(value -> value.getClass().toString())
                .to(topicOut, Produced.with(Serdes.String(), Serdes.String()));
        // for logging
        textLines.print(Printed.toSysOut());

    }
}
