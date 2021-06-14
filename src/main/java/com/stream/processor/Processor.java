package com.stream.processor;

import com.stream.entity.JobOffer;
import com.stream.entity.LandingJobsJob;
import com.stream.entity.Location;
import com.stream.entity.Salary;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    // https://abhishek1987.medium.com/kafka-streams-interactive-queries-9a05ff92d75a

    public static JobOffer createJobOffer(LandingJobsJob landingJobsJob) {
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

    public static String cleanDescription(String description) {
        return Jsoup.parse(description).text();
    }

    public static Location createLocation(LandingJobsJob landingJobsJob) {
        return Location.builder()
                .city(landingJobsJob.getCity())
                .country(landingJobsJob.getCountryName())
                .build();
    }

    public static Salary createSalary(LandingJobsJob landingJobsJob, boolean hasSalary) {
        if (hasSalary) {
            return Salary.builder()
                    .currency(landingJobsJob.getCurrencyCode())
                    .high(landingJobsJob.getSalaryHigh() != null ? landingJobsJob.getSalaryHigh() : 0)
                    .low(landingJobsJob.getSalaryLow() != null ? landingJobsJob.getSalaryLow() : 0)
                    .build();
        }
        return Salary.builder().build();
    }

    public static List<String> createTags(LandingJobsJob landingJobsJob) {
        String description = landingJobsJob.getRoleDescription();
        String upperDescription = description.toUpperCase();

        List<String> tags = new ArrayList<>();
        tags.add("JAVA");

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