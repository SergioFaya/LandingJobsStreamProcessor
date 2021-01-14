package com.stream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.StreamsMetrics;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
class Processor {

    // TODO: in the future deployment extract topics to env variable and get it through docker compose

    @Autowired
    public void process(final StreamsBuilder builder) {
        KStream<String, String> textLines = builder.stream("topic1", Consumed.with(Serdes.String(), Serdes.String()));

        textLines
                .mapValues(value -> value.toUpperCase())
                .to("topic2", Produced.with(Serdes.String(), Serdes.String()));
        // for logging
        textLines.print(Printed.toSysOut());

    }
}
