package com.raimiyashiro.cloudHumans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiException handleValidationExceptions(MethodArgumentNotValidException ex) {
        ApiException exception = new ApiException();

        ex.getBindingResult().getAllErrors().forEach(
                err -> {
                    String fieldName = ((FieldError) err).getField();
                    String errorMessage = err.getDefaultMessage();
                    exception.getErrors().put(fieldName, errorMessage);
                });


        return exception;
    }
}
