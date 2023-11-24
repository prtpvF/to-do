package com.example.todo.Services;

import com.example.todo.Model.Person;
import com.example.todo.Repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public void register(Person person){
        personRepository.save(person);
    }

}