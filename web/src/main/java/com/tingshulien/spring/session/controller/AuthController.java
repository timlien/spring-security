package com.tingshulien.spring.session.controller;

import com.tingshulien.spring.session.model.User;
import com.tingshulien.spring.session.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, @Param("username") String username, @Param("password") String password) {
        User user = authService.register(username, password);
        log.info("User is successfully registered: {}", user);

        try {
            request.login(username, password);
        } catch (ServletException ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }

        return "redirect:/home?success=true";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

    @PostMapping("/password")
    public String changePassword(Authentication authentication, @Param("oldPassword") String oldPassword, @Param("password") String password) {
        if (!authService.changePassword(authentication.getName(), oldPassword, password)) {
            throw new BadCredentialsException("Invalid old password!");
        }

        return "redirect:/";
    }

    @PostMapping("/apple")
    public String test(@RequestParam("test") String test) {
        log.info("test: {}", test);

        return "redirect:/signin";
    }

}
