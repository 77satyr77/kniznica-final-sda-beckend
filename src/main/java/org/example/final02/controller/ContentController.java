package org.example.final02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

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
}
