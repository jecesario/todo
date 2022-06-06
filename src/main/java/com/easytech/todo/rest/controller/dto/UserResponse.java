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
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private boolean admin;
}
