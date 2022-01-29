package com.loanapplication.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return getErrorResponse(ex, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleClientHasAcceptedLoanApplicationException(ClientHasAcceptedLoanApplicationException ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return getErrorResponse(ex, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleClientInfoMismatchedException(ClientInfoMismatchedException ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return getErrorResponse(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return getErrorResponse(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleLoanApplicationNotFoundException(LoanApplicationNotFoundException ex, WebRequest webRequest) {
        log.error(ex.getMessage());
        return getErrorResponse(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String message = "Given parameters not valid";
        String description = ex.getBindingResult().toString();

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, message, description);

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getErrorResponse(Exception ex, WebRequest webRequest, HttpStatus status) {
        Date errorDate = new Date();
        String description = webRequest.getDescription(false);
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, ex.getMessage(), description);
        return new ResponseEntity<>(exceptionResponse, status);
    }

}
