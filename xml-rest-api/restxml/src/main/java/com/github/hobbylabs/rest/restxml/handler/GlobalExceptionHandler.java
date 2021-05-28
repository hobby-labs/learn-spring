package com.github.hobbylabs.rest.restxml.handler;

import com.github.hobbylabs.rest.restxml.exception.RequestedCountryNotFoundException;
import com.github.hobbylabs.rest.restxml.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private HttpHeaders errHeaders = new HttpHeaders();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        /*
         * This method will handle ...
         *     BindException: This exception is thrown when fatal binding errors occur.
         *     MethodArgumentNotValidException: This exception is thrown when argument annotated with @Valid failed validation.
         */
        List<String> errors = new ArrayList<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(errors.toString());

        return new ResponseEntity<>(errorResponse, errHeaders, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        /*
         * This handles 404 error when the client request not existed end point.
         * To handle 404 error manually, set parameters like below are required in your application.properties
         *     spring.mvc.throw-exception-if-no-handler-found=true
         *     spring.resources.add-mappings=false
         */

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.NOT_FOUND);
        errorResponse.setMessage("Endpoint requested by client \"" + ex.getRequestURL() + "\" was not found");

        return new ResponseEntity<Object>(errorResponse, errHeaders, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles RequestedCountryNotFoundException.
     * @param e RequestedCountryNotFoundException
     * @return Error response for the client
     */
    @ExceptionHandler(value = RequestedCountryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRequestedCountryNotFoundException(RequestedCountryNotFoundException e) {
        ErrorResponse errorResponseBody = new ErrorResponse();
        errorResponseBody.setMessage(e.getMessage());
        errorResponseBody.setCode(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorResponseBody, errHeaders, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all exceptions that was not handled by other handlers.
     * @param e Exception occurred in a project
     * @return Error response for the client
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleAsDefault(Exception e) {
        ErrorResponse errorResponseBody = new ErrorResponse();
        errorResponseBody.setMessage(e.getMessage());
        errorResponseBody.setCode(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorResponseBody, errHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostConstruct
    public void postConstruct() {
        errHeaders.add("Content-type", "application/xml");
    }
}