package com.it.academy.springbootqa2.handler;

import com.it.academy.springbootqa2.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final CityErrorResponse cityErrorResponse;
    private final StoreErrorResponse storeErrorResponse;
    private final StreetErrorResponse streetErrorResponse;

    @Autowired
    public GlobalExceptionHandler(CityErrorResponse cityErrorResponse, StoreErrorResponse storeErrorResponse,
                                  StreetErrorResponse streetErrorResponse) {
        this.cityErrorResponse = cityErrorResponse;
        this.storeErrorResponse = storeErrorResponse;
        this.streetErrorResponse = streetErrorResponse;
    }

    @ExceptionHandler
    private ResponseEntity<CityErrorResponse> handleException(CityNotFoundException e) {
        cityErrorResponse.setMessage("City with this id was not found!");
        cityErrorResponse.setTimestamp(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        return new ResponseEntity<>(cityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<StoreErrorResponse> handleException(StoreNotFoundException e) {
        storeErrorResponse.setMessage("Store with this id was not found!");
        storeErrorResponse.setTimestamp(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        return new ResponseEntity<>(storeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<StreetErrorResponse> handleException(StreetNotFoundException e) {
        streetErrorResponse.setMessage("Street with this id was not found!");
        streetErrorResponse.setTimestamp(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        return new ResponseEntity<>(streetErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
