package com.kuehne.city.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class CustomizedExceptionHandler {


    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<Object> handleResultNotFoundException(CustomErrorException exception) {

        Map<String, Object> map = new HashMap<>();
        map.put("message", exception.getMessage());

        if (exception.getHttpStatus() == null && exception.getDate() == null) {
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("date", new Date());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("date", exception.getDate());

        return new ResponseEntity<>(map, exception.getHttpStatus());

    }


}
