package org.example.final02.controller;

import org.example.final02.model.entity.MyUser;
import org.example.final02.repository.MyUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContentController {

    private final MyUserRepository myUserRepository;

    public ContentController(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @GetMapping("/home")
    public String handleWelcome() {
        return "pages/home";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "pages/user_home";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "pages/admin_home";
    }

    @GetMapping("/superAdmin/home")
    public String handleSuperAdminHome(Model model) {
        List<MyUser> users = myUserRepository.findAll();
        model.addAttribute("users", users);
        return "/pages/superAdmin_home";
    }
}
