package com.example.todo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    public String generateToken(UserDetails userDetails){
        //формирование PAYLOAD

        //мапа с данными
        Map<String,Object> claims = new HashMap<>();

        //берем роли через userDetails(права доступа преобразовываем в строку)
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("roles", roleList);
        // в payload Будет поле(массив) roles в котором будут лежать все роли, которые мы указали

        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime()+jwtLifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims) //устанавливает пользовательские данные(в нашем случае роли)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issueDate) //время создания
                .setExpiration(expiredDate) //время истечения токена
                .signWith(SignatureAlgorithm.HS256, secret) //создание подписи (VERIFY SIGNATURE)
                .compact();

    }

    public String getUsername(String token){
        return  getAllClaimsFromToken(token).getSubject();   //берем тему(обертка над методом)
    }

    public List<String> getAllRoles(String token){
        return getAllClaimsFromToken(token).get("roles", List.class); //берем все роли
    }


    //разбор токена на куски(когда приходит)
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret) //когда хотим взять полезные данные, то указываем секретный ключ
                .parseClaimsJws(token)
                .getBody();
    }

}
