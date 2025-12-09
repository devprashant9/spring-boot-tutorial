package com.example.security.controllers;

import com.example.security.models.UserEntity;
import com.example.security.service.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping()
    public String home() {
        return "Home Controller";
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity req,
                                   HttpServletResponse res) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(), req.getPassword()
                )
        );

        String token = JwtUtil.generateToken(req.getUsername());

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        res.addCookie(cookie);

        return ResponseEntity.ok("Logged in");
    }

}
