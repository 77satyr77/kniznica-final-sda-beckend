package org.example.final02.controller;

import org.example.final02.model.entity.MyUser;
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

    /*@PostMapping("/register/user")
    public MyUser register(@RequestBody MyUser myUser) {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return myUserRepository.save(myUser);
    }*/

    @PostMapping("/register/user")
    public ResponseEntity<String> register(@RequestBody MyUser myUser) {
        if (myUserRepository.findByUsername(myUser.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Užívateľ s týmto menom už existuje");
        }
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        MyUser savedUser = myUserRepository.save(myUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Užívateľ úspešne zaregistrovaný" +  savedUser);
    }




}
