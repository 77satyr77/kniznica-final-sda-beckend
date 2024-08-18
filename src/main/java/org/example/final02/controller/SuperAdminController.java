package org.example.final02.controller;

import org.example.final02.model.entity.MyUser;
import org.example.final02.model.entity.UserRole;
import org.example.final02.repository.MyUserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SuperAdminController {

    private final MyUserRepository myUserRepository;

    public SuperAdminController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @PostMapping("/superAdmin/changeRole")
    public String changeRole(@RequestParam String username, @RequestParam UserRole role) {
        Optional<MyUser> optionalMyUser = myUserRepository.findByUsername(username);

        if (optionalMyUser.isPresent()) {
            MyUser myUser = optionalMyUser.get();
            myUser.setRole(role);
            myUserRepository.save(myUser);
        }
        return "redirect:/superAdmin";
    }
}
