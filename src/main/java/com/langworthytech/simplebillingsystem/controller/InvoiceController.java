package com.langworthytech.simplebillingsystem.controller;

import com.langworthytech.simplebillingsystem.dto.request.CreateInvoiceRequest;
import com.langworthytech.simplebillingsystem.dto.response.CreateInvoiceResponse;
import com.langworthytech.simplebillingsystem.dto.response.InvoiceListItemResponse;
import com.langworthytech.simplebillingsystem.dto.response.InvoiceViewResponse;
import com.langworthytech.simplebillingsystem.model.Account;
import com.langworthytech.simplebillingsystem.model.form.InvoiceFormModel;
import com.langworthytech.simplebillingsystem.service.IProductService;
import com.langworthytech.simplebillingsystem.service.ProductService;
import com.langworthytech.simplebillingsystem.security.AuthenticatedUser;
import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import com.langworthytech.simplebillingsystem.security.IAuthenticationFacade;
import com.langworthytech.simplebillingsystem.service.IInvoiceService;
import com.langworthytech.simplebillingsystem.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    private IAuthenticationFacade authenticationFacade;

    private IProductService productService;

    private IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(AuthenticationFacade authenticationFacade, ProductService productService, InvoiceService invoiceService) {
        this.authenticationFacade = authenticationFacade;
        this.productService = productService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, @AuthenticatedUser CustomUserDetails userDetails) {

        // Gather account information
        String userName = userDetails.getFirstName() + " " + userDetails.getLastName();
        model.addAttribute("userName", userName);

        Account account = userDetails.getUser().getAccount();

        CreateInvoiceResponse invoiceForm = new CreateInvoiceResponse();
        invoiceForm.setAccountCompany(account.getCompany());
        invoiceForm.setAccountAddress(account.getAddress());
        invoiceForm.setAccountCity(account.getCity());
        invoiceForm.setAccountState(account.getState());
        invoiceForm.setAccountZip(account.getZip());
        invoiceForm.setAccountEmail(account.getEmail());
        invoiceForm.setAccountPhone(account.getPhone());
        invoiceForm.setAccountWebsite(account.getWebsite());
        invoiceForm.setUserName(userName);

        model.addAttribute("invoice", invoiceForm);

        return "invoice/invoice_form";
    }
    
    @GetMapping("/{id}")
    public String viewInvoice(@PathVariable Long id, Model model, @AuthenticatedUser CustomUserDetails userDetails) {
    	
    	InvoiceViewResponse invoiceView = null;
    	
    	try {
    		invoiceView = invoiceService.findInvoiceById(id);
    		logger.info(invoiceView.toString());
    	} catch(EntityNotFoundException e) {
    		logger.error(e.getMessage());
    		// handle the error here...
    	}
    	
    	
    	String userName = userDetails.getFirstName() + " " + userDetails.getLastName();
        model.addAttribute("userName", userName);
        
        model.addAttribute("invoice", invoiceView);
        
    	return "invoice/invoice_view";
    }

    @PostMapping("")
    public String createInvoice(
    		@ModelAttribute("invoice") InvoiceFormModel invoiceForm,
    		Model model, 
    		@AuthenticatedUser CustomUserDetails userDetails
    ) {

        logger.info(invoiceForm.toString());

        CreateInvoiceResponse invoiceResponse = invoiceService.createInvoice(invoiceForm, userDetails);

        String userName = userDetails.getFirstName() + " " + userDetails.getLastName();
        model.addAttribute("userName", userName);
        
        model.addAttribute("invoice", new CreateInvoiceResponse());
        
        return "redirect:/invoices/" + invoiceResponse.getInvoiceId();
    }

    @PutMapping("/{id}")
    public @ResponseBody CreateInvoiceResponse updateInvoice(
            @ModelAttribute("invoice") CreateInvoiceRequest invoiceRequest,
            Model model,
            @PathVariable Long id) {

        logger.info("Updating invoice # " + id);

        return null;

    }

    @GetMapping("")
    public String showInvoices(Model model) {

        CustomUserDetails userDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();

        List<InvoiceListItemResponse> invoiceItems = invoiceService.findAllInvoices();

        model.addAttribute("invoices", invoiceItems);
        model.addAttribute("userName", userDetails.getFirstName() + " " + userDetails.getLastName());
        return "invoice/invoice_list";
    }

}
