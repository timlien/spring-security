package com.tingshulien.spring.session.controller;

import com.tingshulien.spring.session.model.User;
import com.tingshulien.spring.session.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/register")
    public String create(@Param("email") String email, @Param("password") String password) {
        User user = User.newInstance(email, passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @PostMapping("/login")
    public String login(Authentication authentication) {
        return (authentication != null && authentication.isAuthenticated())
                ? "redirect:/"
                : "redirect:/signin";
    }

}
