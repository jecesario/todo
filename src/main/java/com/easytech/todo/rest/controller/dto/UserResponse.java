package com.easytech.todo.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class UserRequest {

    @NotEmpty(message = "O campo username precisa ser preenchido!")
    @Size(max = 15)
    private String username;

    @Email(message = "O campo email precisa ser preenchido com um email válido!")
    private String email;

    @NotEmpty(message = "O campo password precisa ser preenchido!")
    private String password;
}
