package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.invoice.dto.*;
import com.langworthytech.simplebillingsystem.invoice.dto.InvoiceItemFormModel;
import com.langworthytech.simplebillingsystem.product.IProductService;
import com.langworthytech.simplebillingsystem.product.ProductService;
import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import com.langworthytech.simplebillingsystem.security.IAuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String showCreateForm(Model model) {

        // Gather account information
        CustomUserDetails userDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();
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

        return "invoice_form";
    }

    @PostMapping("")
    public @ResponseBody CreateInvoiceResponse createInvoice(@RequestBody InvoiceFormModel invoiceForm) {

        logger.info(invoiceForm.toString());

        CreateInvoiceResponse invoiceResponse = invoiceService.createInvoice(invoiceForm);

        return invoiceResponse;
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
        return "invoice_list";
    }

}
