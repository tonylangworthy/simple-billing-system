package com.langworthytech.simplebillingsystem.customer;

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
        model.addAttribute("customer", new CustomerFormModel());
        return "create_customer";
    }

    @PostMapping("/create")
    public String create(
            @Valid
            @ModelAttribute("customer") CustomerFormModel formModel,
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
            return "redirect:/customers/create";
        }
        // Store the customer
        Customer customer = customerService.findOrCreateCustomer(formModel);
        redirectAttributes.addFlashAttribute("successMessage", "Customer created successfully!");
        return "redirect:/customers/create";
    }
}
