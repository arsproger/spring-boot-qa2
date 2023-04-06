package com.it.academy.springbootqa2.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CityErrorResponse {
    private String message;
    private String timestamp;
}
