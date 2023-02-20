package com.easytech.todo.rest.controller.exceptions;

import lombok.Getter;

import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String message) {
        this.errors = List.of(message);
    }
}
