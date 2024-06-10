package com.calendar.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setHttpStatus(status);
        exceptionResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(exceptionResponse, status);
    }
    @ExceptionHandler(value = {DateMismatchException.class})
    public ResponseEntity<ExceptionResponse> handleDateMismatchException(DateMismatchException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setHttpStatus(status);
        exceptionResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
