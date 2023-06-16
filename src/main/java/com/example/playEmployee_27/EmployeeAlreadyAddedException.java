package com.example.playEmployee_27;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus/*(HttpStatus.ALREADY_REPORTED)*/
public class EmployeeAlreadyAddedException extends RuntimeException {

    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }
}
