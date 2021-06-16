package com.stream.processor;

import com.stream.config.CustomConfig;
import com.stream.entity.JobOffer;
import com.stream.entity.LandingJobsJob;
import com.stream.entity.Location;
import com.stream.entity.Salary;
import com.stream.serde.JobOfferSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.state.KeyValueStore;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
class Processor {
    // https://abhishek1987.medium.com/kafka-streams-interactive-queries-9a05ff92d75a


    @Autowired
    private CustomConfig customConfig;

    @Bean
    public Consumer<KStream<String, LandingJobsJob>> process() {

        return input -> input
                .map((key, value) -> new KeyValue<String, JobOffer>(value.getId() + "", createJobOffer(value)))
                .toTable(Named.as(customConfig.tableName),
                        Materialized.<String, JobOffer, KeyValueStore<Bytes, byte[]>>as(
                                customConfig.tableName)
                                .withKeySerde(Serdes.String())
                                .withValueSerde(new JobOfferSerde()));

    }

    public JobOffer createJobOffer(LandingJobsJob landingJobsJob) {
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

    public String cleanDescription(String description) {
        return Jsoup.parse(description).text();
    }

    public Location createLocation(LandingJobsJob landingJobsJob) {
        return Location.builder()
                .city(landingJobsJob.getCity())
                .country(landingJobsJob.getCountryName())
                .build();
    }

    public Salary createSalary(LandingJobsJob landingJobsJob, boolean hasSalary) {
        if (hasSalary) {
            return Salary.builder()
                    .currency(landingJobsJob.getCurrencyCode())
                    .high(landingJobsJob.getSalaryHigh() != null ? landingJobsJob.getSalaryHigh() : 0)
                    .low(landingJobsJob.getSalaryLow() != null ? landingJobsJob.getSalaryLow() : 0)
                    .build();
        }
        return Salary.builder().build();
    }

    public List<String> createTags(LandingJobsJob landingJobsJob) {
        String description = landingJobsJob.getRoleDescription();
        String upperDescription = description.toUpperCase();

        List<String> tags = customConfig.processorTags.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> myTags = new ArrayList<String>(
                landingJobsJob
                        .getTags()
                        .stream()
                        .map(String::toUpperCase)
                        .collect(Collectors.toList())
        );

        for (String tag : tags) {
            if (upperDescription.contains(tag)) {
                myTags.add(tag);
            }
        }
        return myTags;
    }

}