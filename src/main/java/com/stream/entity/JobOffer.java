package com.stream.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class JobOffer {
    @JsonProperty("url_link")
    private String urlLink;
    @JsonProperty("has_salary")
    private boolean hasSalary;
    private Salary salary;
    private String description;
    private String company;
    private Location location;
    private String title;
    @JsonProperty("published_at")
    private String publishedAt;
    private boolean remote;
    private List<String> tags;
}