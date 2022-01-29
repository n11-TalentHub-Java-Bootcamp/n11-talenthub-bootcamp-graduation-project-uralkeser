package com.loanapplication.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        return getErrorResponse(ex, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler
//    public final ResponseEntity<Object> handleIDNumberDoesNotExistException(IDNumberDoesNotExistException ex, WebRequest webRequest) {
//        return getErrorResponse(ex, webRequest, HttpStatus.NOT_FOUND);
//    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String message = "Validation failed!";
        String description = ex.getBindingResult().toString();

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, message, description);

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getErrorResponse(Exception ex, WebRequest webRequest, HttpStatus notFound) {
        Date errorDate = new Date();
        String description = webRequest.getDescription(false);
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, ex.getMessage(), description);
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

}
