package com.kuehne.city.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;
    private Date date;

    public CustomErrorException(String message, HttpStatus httpStatus, Date date) {
        super(message);
        this.httpStatus = httpStatus;
        this.date = date;
    }


}
