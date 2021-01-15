package com.stream.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LandingJobsJob {

    private int id;
    private int reward;
    @JsonProperty("work_from_home")
    private boolean workFromHome;
    @JsonProperty("company_id")
    private int companyId;
    private String city;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("nice_to_have")
    private String niceToHave;
    @JsonProperty("successful?")
    private boolean successful;
    private boolean remote;
    private String title;
    private String type;
    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("role_description")
    private String roleDescription;
    private List<String> tags;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("expires_at")
    private String expiresAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("salary_low")
    private Object salaryLow;
    @JsonProperty("salary_high")
    private Object salaryHigh;
    private String perks;
    @JsonProperty("published_at")
    private String publishedAt;
    @JsonProperty("relocation_paid")
    private boolean relocationPaid;

}