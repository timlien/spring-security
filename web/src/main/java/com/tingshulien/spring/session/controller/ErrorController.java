package com.tingshulien.spring.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class ErrorController {

    @GetMapping("/403")
    public String error403(Model model) {
        model.addAttribute("errorMessage", "Access Denied");
        model.addAttribute("statusCode", 403);
        return "error";
    }

}
