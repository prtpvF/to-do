package com.example.todo.Controllers;

import com.example.todo.Model.Person;
import com.example.todo.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://localhost:3000")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/registration")
    public void register(@RequestBody Person person) {
        personService.register(person);
    }


}
