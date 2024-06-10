package com.calendar.calendar.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {
    private String message;
    private HttpStatus httpStatus;
    private Date timestamp;

    public ExceptionResponse(HttpStatus httpStatus, String message, Date timestamp) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ExceptionResponse() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
