package com.example.todo.dtos;

import com.example.todo.Model.Role;
import lombok.Data;

import java.util.Collection;

@Data

public class RegistrationPersonDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;


}
