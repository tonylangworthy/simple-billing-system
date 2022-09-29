package com.langworthytech.simplebillingsystem.dto.request;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class CustomerFormRequest {

    @NotEmpty
    @Size(max = 20, message = "First name cannot be greater than 20 characters.")
    private String firstName;

    @NotEmpty
    @Size(max = 20, message = "Last name cannot be greater than 20 characters.")
    private String lastName;

    @NotEmpty
    @Size(max = 50, message = "Email cannot be greater than 50 characters.")
    private String email;

    @NotEmpty
    @Size(max = 12, message = "Phone number cannot be greater than 20 characters.")
    private String phone;

    @Size(max = 100, message = "Company name cannot be greater than 100 characters.")
    private String companyName;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CustomerFormModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
