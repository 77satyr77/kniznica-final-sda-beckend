package org.example.final02.controller;

import org.example.final02.model.entity.MyUser;
import org.example.final02.repository.MyUserRepository;
import org.example.final02.service.MyUserDetailService;
import org.example.final02.webtoken.AuthResponse;
import org.example.final02.webtoken.JwtService;
import org.example.final02.webtoken.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentController {

    private final MyUserRepository myUserRepository;
    //musime poskytnut OVEROVATELA a to v SecurityConfiguration
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService myUserDetailService;
    private final JwtService jwtService;

    public ContentController(MyUserRepository myUserRepository, AuthenticationManager authenticationManager, MyUserDetailService myUserDetailService, JwtService jwtService) {
        this.myUserRepository = myUserRepository;
        this.authenticationManager = authenticationManager;
        this.myUserDetailService = myUserDetailService;
        this.jwtService = jwtService;
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


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.username()));
        }else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
