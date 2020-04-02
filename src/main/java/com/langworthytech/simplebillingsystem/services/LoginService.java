package com.langworthytech.simplebillingsystem.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String email, String password) {
        return email.equalsIgnoreCase("tony@webbdealer.com")
                && password.equalsIgnoreCase("pass");
    }
}
