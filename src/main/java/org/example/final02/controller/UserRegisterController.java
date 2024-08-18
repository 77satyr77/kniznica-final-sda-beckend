package org.example.final02.controller;

import org.example.final02.model.entity.MyUsers;
import org.example.final02.repository.MyUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {

    //todo dorobit sablonu pre registraciu.

    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterController(MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register/user")
    public ResponseEntity<String> register(@RequestBody MyUsers myUsers) {
        if (myUserRepository.findByUsername(myUsers.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Užívateľ s týmto menom už existuje");
        }
        myUsers.setPassword(passwordEncoder.encode(myUsers.getPassword()));
        MyUsers savedUser = myUserRepository.save(myUsers);

        return ResponseEntity.status(HttpStatus.CREATED).body("Užívateľ úspešne zaregistrovaný" +  savedUser);
    }




}
