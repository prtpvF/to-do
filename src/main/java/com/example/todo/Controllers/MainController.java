package com.example.todo.Controllers;

import com.example.todo.Services.AuthService;
import com.example.todo.dtos.RegistrationPersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final AuthService authService;

    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "unsecured data";
    }

    @GetMapping("/secured")
    public String securedData() {
        return "secured data";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/admin")
    public String admin() {
        System.out.println("вызван");
        return "Admin data";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationPersonDto registrationPersonDto) {
      return authService.createNewUser(registrationPersonDto);
    }
}
