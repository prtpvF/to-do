package com.example.todo.Services;

import com.example.todo.Model.Person;
import com.example.todo.dtos.JwtRequest;
import com.example.todo.dtos.JwtResponse;
import com.example.todo.dtos.RegistrationPersonDto;
import com.example.todo.dtos.UserDto;
import com.example.todo.exceptions.AppError;
import com.example.todo.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PersonService personService;
    private final JwtTokenUtils jwtTokenUtils;
    private  final AuthenticationManager authenticationManager;


    public ResponseEntity<?> createNewUser(@RequestBody RegistrationPersonDto registrationPersonDto) {
        if (!registrationPersonDto.getPassword().equals(registrationPersonDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "пароли не совпадают"), HttpStatus.UNAUTHORIZED);
        }
        if (personService.findByUsername(registrationPersonDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именнем уже существует"), HttpStatus.BAD_REQUEST);
        }

        Person person =  personService.createNewUser(registrationPersonDto);
        return ResponseEntity.ok(new UserDto(person.getId(), person.getUsername(),person.getEmail()));
    }


    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return  new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "некорктный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        System.out.println("вызван");

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
