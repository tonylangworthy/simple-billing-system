package com.langworthytech.simplebillingsystem.security;

import com.langworthytech.simplebillingsystem.entities.CustomUser;
import com.langworthytech.simplebillingsystem.models.RegistrationFormModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.StringBufferInputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CustomUserController {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserController.class);

    private CustomUserService userService;

    @Autowired
    public CustomUserController(CustomUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(ModelMap modelMap, Principal principal) {
        modelMap.put("userName", principal.getName());

        return "hello";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registration", new RegistrationFormModel());
        return "registration";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(
            @Valid
            @ModelAttribute("registration") RegistrationFormModel registrationFormModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            ModelMap model
            ) {
        if(bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(e -> {
                FieldError fieldError = (FieldError) e;
                logger.info("Object name: " + fieldError.getField());
                logger.info("Default Message: " + e.getDefaultMessage());
                RegistrationValidationError regError = new RegistrationValidationError();
                regError.setFieldName(fieldError.getField());
                regError.setHasErrors(true);
                regError.setErrorMessage(fieldError.getDefaultMessage());
                redirectAttributes.addFlashAttribute(regError.getFieldName(), regError);

            });
            logger.info(registrationFormModel.getFirstName());
            logger.info(registrationFormModel.getLastName());
            logger.info(registrationFormModel.getEmail());
            logger.info(registrationFormModel.getFirstPassword());
            logger.info(registrationFormModel.getConfirmPassword());

            model.addAttribute("firstName", registrationFormModel.getFirstName());
            model.addAttribute("lastName", registrationFormModel.getLastName());
            model.addAttribute("email", registrationFormModel.getEmail());
            model.addAttribute("firstPassword", registrationFormModel.getFirstPassword());
            model.addAttribute("confirmPassword", registrationFormModel.getConfirmPassword());

            //logger.info(redirectAttributes.getFlashAttributes().toString());
            return "redirect:/register";
        }

        // If validation passes, save user
        userService.registerNewUser(registrationFormModel);

        return "hello";

    }

}
