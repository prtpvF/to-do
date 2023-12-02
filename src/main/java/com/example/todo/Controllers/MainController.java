package com.example.todo.Controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/unsecured")
    public String unsecuredData(){
        return "unsecured data";
    }

    @GetMapping("/secured")
    public String securedData(){
        return "secured data";
    }

    @GetMapping("/info")
    public String userData(Principal principal){
        return  principal.getName();
    }
}
