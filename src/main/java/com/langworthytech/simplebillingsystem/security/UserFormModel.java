package com.langworthytech.simplebillingsystem.account;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.*;

@Component
public class RegistrationFormModel {

    @NotEmpty
    @Size(max = 20, message = "First name cannot be greater than 20 characters.")
    private String firstName;

    @NotEmpty
    @Size(max = 20, message = "Last name cannot be greater than 20 characters.")
    private String lastName;

    @NotEmpty
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Password must be at least 8 characters.")
    private String firstPassword;

    @NotEmpty
    @Size(min = 8, message = "Password must be at least 8 characters.")
    private String confirmPassword;

//    private boolean isPasswordMatch;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @AssertTrue(message = "Passwords do not match. Please try again.")
    public boolean getIsPasswordMatch() {
        System.out.println("first password " + firstPassword);
        System.out.println("second password " + confirmPassword);
        if(firstPassword.equals(confirmPassword)) {
            System.out.println("passwords match!");
            return true;
        }
        return false;
    }
}
