package com.easytech.todo.rest.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SubtaskRequest {

    @NotEmpty(message = "O campo title precisa ser preenchido!")
    private String title;

    @NotNull(message = "O campo status não pode ser nulo!")
    private Boolean status;

}
