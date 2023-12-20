package com.example.todo.Services;

import com.example.todo.Model.Person;
import com.example.todo.Model.Role;
import com.example.todo.Repository.PersonRepository;
import com.example.todo.dtos.RegistrationPersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    private final  PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    public Optional<Person> findByUsername(String username){
        return  personRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("пользователь '%s' не найден", username)));

        return new User(person.getUsername(), person.getPassword(), person.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }

    //todo
    public Person createNewUser(RegistrationPersonDto registrationUserDto){
        Person person = new Person();
        person.setEmail(registrationUserDto.getEmail());
        person.setUsername(registrationUserDto.getUsername());
        person.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        person.setRoles(List.of(roleService.getUserRole()));
        person.setProductivity(0);
        return personRepository.save(person);

    }


}