package com.stream.processor;

import com.stream.entity.JobOffer;
import com.stream.entity.LandingJobsJob;
import com.stream.entity.Location;
import com.stream.entity.Salary;
import com.stream.serdes.SerdesFactory;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class Processor {
    // https://abhishek1987.medium.com/kafka-streams-interactive-queries-9a05ff92d75a

    @Value("${PROCESSOR_TOPIC_OUT:topic2}")
    private String topicOut;

    @Value("${TAGS_KEYWORD}")
    private List<String> tags;

    @Autowired
    private KStream<String, LandingJobsJob> kafkaStream;

    @Autowired
    public void process() {
        // kafka-console-producer.sh --broker-list localhost:9092 --topic topic-name --property "parse.key=true" --property "key.separator=:"
        // KTable<Integer, JobOffer> table =
        KStream<String, JobOffer> stream = kafkaStream
                .map((key, job) -> keyMapper(job))
                .mapValues(this::createJobOffer);

        String table = "temp-filter-table";
// table is used to avoid duplicated values
        KTable<String, JobOffer> tempTable = stream.toTable(Named.as(table),
                Materialized.<String, JobOffer, KeyValueStore<Bytes, byte[]>>as(
                        table /* table/store name */)
                        .withKeySerde(Serdes.String()) /* key serde */
                        .withValueSerde(SerdesFactory.jobOfferSerdes()) /* value serde */
        );

        KStream<String, JobOffer> finalStream = tempTable.toStream(Named.as(topicOut));

        // logging
        kafkaStream.print(Printed.toSysOut());
        finalStream.print(Printed.toSysOut());
    }

    private KeyValue<String, LandingJobsJob> keyMapper(LandingJobsJob job) {
        return new KeyValue("JOB" + job.getId(), job);
    }


    private JobOffer createJobOffer(LandingJobsJob landingJobsJob) {
        boolean hasSalary = landingJobsJob.getSalaryHigh() != null || landingJobsJob.getSalaryLow() != null;
        return JobOffer.builder()
                .urlLink(landingJobsJob.getUrl())
                .company(landingJobsJob.getCompany())
                .hasSalary(hasSalary)
                .salary(createSalary(landingJobsJob, hasSalary))
                .description(cleanDescription(landingJobsJob.getRoleDescription()))
                .title(landingJobsJob.getTitle())
                .location(createLocation(landingJobsJob))
                .publishedAt(landingJobsJob.getPublishedAt())
                .remote(landingJobsJob.isRemote())
                .tags(createTags(landingJobsJob))
                .companyLogoUrl(landingJobsJob.getCompanyLogoUrl())
                .build();
    }

    private String cleanDescription(String description) {
        return Jsoup.parse(description).text();
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
        String description = landingJobsJob.getRoleDescription();
        String upperDescription = description.toUpperCase();

        // List<String> myTags = new ArrayList<>(landingJobsJob.getTags());
        List<String> myTags = new ArrayList<>();

        for (String tag : tags) {
            if (upperDescription.contains(tag.toUpperCase())) {
                myTags.add(tag);
            }
        }
        return myTags;
    }
}
