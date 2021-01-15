package com.stream.entity;

import lombok.Builder;

@Builder
public class Salary {
    private double high;
    private double low;
    private String currency;
}
