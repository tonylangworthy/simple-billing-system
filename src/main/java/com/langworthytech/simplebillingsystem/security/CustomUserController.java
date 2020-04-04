package com.langworthytech.simplebillingsystem.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class CustomUserController {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserController.class);

    private CustomUserService userService;

    @Autowired
    public CustomUserController(CustomUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(ModelMap modelMap, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        modelMap.put("userName", userDetails.getFirstName() + " " + userDetails.getLastName());
        logger.info("Currently Logged in User: " + userDetails.getFirstName() + " " + userDetails.getLastName());
        return "dashboard";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
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
