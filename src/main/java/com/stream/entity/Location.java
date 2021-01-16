package com.stream.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Location {
    private String country;
    private String city;
}
