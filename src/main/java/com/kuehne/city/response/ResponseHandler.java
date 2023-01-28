package com.kuehne.city.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {


    public ResponseEntity<Object> generateResponse(String message, HttpStatus httpStatus, Object response) {

        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("httpStatus", httpStatus);
        map.put("response", response);

        return new ResponseEntity<>(map, httpStatus);

    }
}
