package com.langworthytech.simplebillingsystem.controller;

import com.langworthytech.simplebillingsystem.dto.request.CustomerFormRequest;
import com.langworthytech.simplebillingsystem.dto.response.CustomerResponse;
import com.langworthytech.simplebillingsystem.model.Customer;
import com.langworthytech.simplebillingsystem.service.CustomerService;
import com.langworthytech.simplebillingsystem.util.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new CustomerFormRequest());
        return "create_customer";
    }

    @PostMapping("")
    public @ResponseBody CustomerResponse create(
            @Valid
            @ModelAttribute("customer") CustomerFormRequest formModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            ModelMap model
    ) {
        logger.info(formModel.toString());

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                FieldError fieldError = (FieldError) e;
                ValidationError regError = new ValidationError();
                regError.setFieldName(fieldError.getField());
                regError.setHasErrors(true);
                regError.setErrorMessage(fieldError.getDefaultMessage());
                redirectAttributes.addFlashAttribute(regError.getFieldName(), regError);

            });
            return new CustomerResponse();
        }
        // Store the customer
        Customer customer = customerService.findOrCreateCustomer(formModel);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setPhone(customer.getPhone());
        customerResponse.setCompanyName(customer.getCompanyName());

        return customerResponse;
    }

    @GetMapping("/autocomplete/{term}")
    public @ResponseBody
    List<CustomerResponse> searchCustomerByEmailStartsWith(@PathVariable String term) {
        List<CustomerResponse> customers = new ArrayList<>();
        customerService.searchByEmailStartsWith(term).forEach(customer -> {
            customers.add(new CustomerResponse(
                    customer.getId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getCompanyName()
            ));
        });
        return customers;
    }

}
