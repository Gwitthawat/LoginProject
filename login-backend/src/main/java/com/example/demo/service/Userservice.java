package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.Passconfig;
import com.example.demo.model.Usermodel;
import com.example.demo.repository.UserRepository;

@Service
public class Userservice {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usermodel> getAll(){
        return userRepository.findAll();
    }

    public Usermodel register(Usermodel user){
       if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
       }
       String hashedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(hashedPassword);
       user.setRole("USER");
       return userRepository.save(user);
    }

    public String login(String user,String rawPassword){
        Optional<Usermodel> founduser = userRepository.findByUsername(user);
        if(founduser.isEmpty()){
            throw new RuntimeException("Not found User");
        }

        Usermodel name = founduser.get();

        if(!passwordEncoder.matches(rawPassword,name.getPassword())){
            throw new RuntimeException("Password not matches");
        }
        return "Login Success";
    }
}
