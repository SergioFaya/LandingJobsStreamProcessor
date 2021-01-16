package com.stream.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Salary {
    private double high;
    private double low;
    private String currency;
}
