package com.example.todo.Services;

import com.example.todo.Model.Person;
import com.example.todo.Repository.PersonRepository;
import com.example.todo.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    private final EntityManagerFactory entityManagerFactory;
    private final  PersonRepository personRepository;
    private final  RoleRepository roleRepository;




    public void register(Person person){
        person.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        personRepository.save(person);
    }



    public Optional<Person> findByUsername(String username){
        return  personRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("пользователь '%s' не найден", username)));

        return new User(person.getUsername(), person.getPassword(), person.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }


}