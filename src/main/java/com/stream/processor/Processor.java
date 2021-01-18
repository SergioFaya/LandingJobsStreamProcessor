package com.stream.processor;

import com.stream.entity.JobOffer;
import com.stream.entity.LandingJobsJob;
import com.stream.entity.Location;
import com.stream.entity.Salary;
import com.stream.serdes.SerdesFactory;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class Processor {

    @Value("${PROCESSOR_TOPIC_IN:topic1}")
    private String topicIn;

    @Value("${PROCESSOR_TOPIC_OUT:topic2}")
    private String topicOut;

    @Autowired
    public void process(final StreamsBuilder builder) {
        KStream<String, LandingJobsJob> stream = builder.stream(topicIn, Consumed.with(Serdes.String(),
                SerdesFactory.landingJobsJobSerdes()));

        stream
                .mapValues(this::createJobOffer)
                .to(topicOut, Produced.with(Serdes.String(), SerdesFactory.jobOfferSerdes()));

        // logging
        stream.print(Printed.toSysOut());
    }

    private JobOffer createJobOffer(LandingJobsJob landingJobsJob) {
        boolean hasSalary = landingJobsJob.getSalaryHigh() != null || landingJobsJob.getSalaryLow() != null;
        return JobOffer.builder()
                .urlLink("")
                .company(landingJobsJob.getCompany())
                .hasSalary(hasSalary)
                .salary(createSalary(landingJobsJob, hasSalary))
                .description(landingJobsJob.getRoleDescription())
                .title(landingJobsJob.getTitle())
                .location(createLocation(landingJobsJob))
                .publishedAt(landingJobsJob.getPublishedAt())
                .remote(landingJobsJob.isRemote())
                .tags(createTags(landingJobsJob))
                .build();
    }

    private Location createLocation(LandingJobsJob landingJobsJob) {
        return Location.builder()
                .city(landingJobsJob.getCity())
                .country(landingJobsJob.getCountryName())
                .build();
    }

    private Salary createSalary(LandingJobsJob landingJobsJob, boolean hasSalary) {
        if (hasSalary) {
            return Salary.builder()
                    .currency(landingJobsJob.getCurrencyCode())
                    .high(landingJobsJob.getSalaryHigh() != null ? landingJobsJob.getSalaryHigh() : 0)
                    .low(landingJobsJob.getSalaryLow() != null ? landingJobsJob.getSalaryLow() : 0)
                    .build();
        }
        return Salary.builder().build();
    }

    private List<String> createTags(LandingJobsJob landingJobsJob) {
        // TODO: create more tags reviewing the description of the job based on keywords
        return landingJobsJob.getTags();
    }
}
