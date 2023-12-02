package com.example.todo.Controllers;

import com.example.todo.Model.Person;
import com.example.todo.Services.PersonService;
import com.example.todo.dtos.JwtRequest;
import com.example.todo.dtos.JwtResponse;
import com.example.todo.exceptions.AppError;
import com.example.todo.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final PersonService personService;
    private final JwtTokenUtils jwtTokenUtils;
    private  final AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return  new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "некорктный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


}
