package com.stream.serdes;

import com.stream.entity.JobOffer;
import com.stream.entity.LandingJobsJob;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.util.HashMap;
import java.util.Map;

public class SerdesFactory {

    public static final String JSON_POJO_CLASS = "JsonPOJOClass";

    private SerdesFactory() {
    }

    public static Serde<LandingJobsJob> landingJobsJobSerdes() {
        Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<LandingJobsJob> serializer = new JsonPOJOSerializer<>();
        serdeProps.put(JSON_POJO_CLASS, LandingJobsJob.class);
        serializer.configure(serdeProps, false);

        final Deserializer<LandingJobsJob> deserializer = new JsonPOJODeserializer<>();
        serdeProps.put(JSON_POJO_CLASS, LandingJobsJob.class);
        deserializer.configure(serdeProps, false);

        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<JobOffer> jobOfferSerdes() {
        Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<JobOffer> serializer = new JsonPOJOSerializer<>();
        serdeProps.put(JSON_POJO_CLASS, JobOffer.class);
        serializer.configure(serdeProps, false);

        final Deserializer<JobOffer> deserializer = new JsonPOJODeserializer<>();
        serdeProps.put(JSON_POJO_CLASS, JobOffer.class);
        deserializer.configure(serdeProps, false);

        return Serdes.serdeFrom(serializer, deserializer);
    }


}
