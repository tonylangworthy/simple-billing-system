package com.langworthytech.simplebillingsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    public HomeController() {

    }

    @GetMapping("")
    public String index() {
        return "index";
    }
}
