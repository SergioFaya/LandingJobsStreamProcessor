package com.stream.serdes;

import com.stream.entity.LandingJobsJob;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.util.HashMap;
import java.util.Map;

public class SerdesFactory {

    private SerdesFactory(){ }

    public final static Serde<LandingJobsJob> landingJobsJobSerde(){
        Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<LandingJobsJob> pageViewSerializer = new JsonPOJOSerializer();
        serdeProps.put("JsonPOJOClass", LandingJobsJob.class);
        pageViewSerializer.configure(serdeProps, false);

        final Deserializer<LandingJobsJob> pageViewDeserializer = new JsonPOJODeserializer();
        serdeProps.put("JsonPOJOClass", LandingJobsJob.class);
        pageViewDeserializer.configure(serdeProps, false);

        final Serde<LandingJobsJob> landingJobsJobSerde = Serdes.serdeFrom(pageViewSerializer, pageViewDeserializer);
        return landingJobsJobSerde;
    }



}
