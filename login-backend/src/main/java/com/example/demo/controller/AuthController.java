package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Usermodel;
import com.example.demo.service.Userservice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private Userservice userservice;

    @PostMapping("/register")
    public UserDTO register(@RequestBody Usermodel user){
        Usermodel saved = userservice.register(user);

        UserDTO dto = new UserDTO();
        dto.setId(saved.getId());
        dto.setUsername(saved.getUsername());
        dto.setRole(saved.getRole());
        return dto;
    }

    @PostMapping("/login")
    public String login(@RequestBody Usermodel user){
        return userservice.login(user.getUsername(), user.getPassword());
    }
    
    
}
