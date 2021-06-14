package com.stream.serde;


import com.stream.entity.LandingJobsJob;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.HashMap;
import java.util.Map;

public class LandingJobsJobSerde implements Serde<LandingJobsJob> {

    private final String JSON_POJO_CLASS = "JsonPOJOClass";

    @Override
    public Serializer<LandingJobsJob> serializer() {
        Map<String, Object> serdeProps = new HashMap<>();
        final Serializer<LandingJobsJob> serializer = new JsonPOJOSerializer<>();
        serdeProps.put(JSON_POJO_CLASS, LandingJobsJob.class);
        serializer.configure(serdeProps, false);
        return serializer;
    }

    @Override
    public Deserializer<LandingJobsJob> deserializer() {
        Map<String, Object> serdeProps = new HashMap<>();
        final Deserializer<LandingJobsJob> deserializer = new JsonPOJODeserializer<>();
        serdeProps.put(JSON_POJO_CLASS, LandingJobsJob.class);
        deserializer.configure(serdeProps, false);
        return deserializer;
    }
}