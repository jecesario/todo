package com.easytech.todo.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserRequest {

    @NotEmpty(message = "O campo username precisa ser preenchido!")
    @Size(max = 15)
    private String username;

    @NotEmpty(message = "O campo email precisa ser preenchido!")
    @Size(max = 10)
    private String email;
}
