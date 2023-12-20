package com.example.todo.dtos;

import com.example.todo.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
        private int id;
        private String username;
        private String email;












    }
