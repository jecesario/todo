package com.easytech.todo.rest.controller.exceptions;

import com.easytech.todo.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlerObjectNotFoundException(ObjectNotFoundException e) {
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerObjectNotFoundException(ConstraintViolationException e) {
        return new ApiErrors(e.getConstraintViolations().stream().map(errors -> errors.getMessageTemplate()).collect(Collectors.toList()));
    }
}
