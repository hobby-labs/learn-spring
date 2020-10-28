package com.github.hobbylabs.httpstatushandling.app;

import com.github.hobbylabs.httpstatushandling.app.exceptions.BarException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(BarException.class)
    public void conflict() {
        // Nothing to do
    }
}
