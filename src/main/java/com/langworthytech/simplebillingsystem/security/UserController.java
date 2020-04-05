package com.langworthytech.simplebillingsystem.security;

import com.langworthytech.simplebillingsystem.util.ValidationError;
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
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

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
        String successMessage = null;
        if(error != null) {
            errorMessage = "Username or Password is incorrect!";
            model.addAttribute("errorMessage", errorMessage);
        }
        if(logout != null) {
            successMessage = "You have been successfully logged out";
            model.addAttribute("successMessage", successMessage);
        }

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
            ModelMap model,
            HttpServletRequest request
            ) {
        if(bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(e -> {
                FieldError fieldError = (FieldError) e;
                logger.info("Object name: " + fieldError.getField());
                logger.info("Default Message: " + e.getDefaultMessage());
                ValidationError regError = new ValidationError();
                regError.setFieldName(fieldError.getField());
                regError.setHasErrors(true);
                regError.setErrorMessage(fieldError.getDefaultMessage());

                redirectAttributes.addFlashAttribute(regError.getFieldName(), regError);

            });

//            redirectAttributes.addFlashAttribute("firstName", registrationFormModel.getFirstName());

//            model.addAttribute("firstName", registrationFormModel.getFirstName());
//            model.addAttribute("lastName", registrationFormModel.getLastName());
//            model.addAttribute("email", registrationFormModel.getEmail());
//            model.addAttribute("firstPassword", registrationFormModel.getFirstPassword());
//            model.addAttribute("confirmPassword", registrationFormModel.getConfirmPassword());

            //logger.info(redirectAttributes.getFlashAttributes().toString());
            return "redirect:/register";
        }

        // If validation passes, save user
        try {
            User user = userService.registerNewUser(registrationFormModel);
        } catch (EmailExistsException e) {

            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        } catch (RoleNotFoundException e) {
            logger.error(e.getMessage());
        }

        // Redirect to the login page with a registration successful message
        redirectAttributes.addFlashAttribute("successMessage", "Registration Successful! Please log in.");
        return "redirect:/login";

    }

}
