package com.it.academy.springbootqa2.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class StoreDTO {
    private String name;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
