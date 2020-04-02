package com.langworthytech.simplebillingsystem.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.StringBufferInputStream;
import java.security.Principal;

@Controller
public class AuthenticationController {

    @GetMapping("/hello")
    public String hello(ModelMap modelMap, Principal principal) {
        modelMap.put("userName", principal.getName());

        return "hello";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String authenticate() {
//
//    }
}
