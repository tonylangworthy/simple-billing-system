package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.invoice.dto.CreateInvoiceItemRequest;
import com.langworthytech.simplebillingsystem.invoice.dto.CreateInvoiceRequest;
import com.langworthytech.simplebillingsystem.invoice.dto.CreateInvoiceResponse;
import com.langworthytech.simplebillingsystem.invoice.dto.InvoiceItemResponse;
import com.langworthytech.simplebillingsystem.product.IProductService;
import com.langworthytech.simplebillingsystem.product.Product;
import com.langworthytech.simplebillingsystem.product.ProductService;
import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import com.langworthytech.simplebillingsystem.security.IAuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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
        return "invoice_create";
    }

    @PostMapping("/invoice-items")
    public @ResponseBody
    InvoiceItemResponse createInvoiceItem(@ModelAttribute("invoiceItem") CreateInvoiceItemRequest invoiceItemForm) {

        logger.info("form Data: " + invoiceItemForm.toString());
        Product product = null;

        if(invoiceItemForm.getProductId() != null) {
            Optional<Product> optionalProduct = productService.findProductById(invoiceItemForm.getProductId());
            product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        }
        String name = invoiceItemForm.getProductName();
        String desc = invoiceItemForm.getProductDescription();

        product = productService.createProduct(new Product(name, desc));


        Invoice invoice = invoiceService.createDraftInvoice();
        InvoiceItem invoiceItem = invoiceService.createInvoiceItem(invoiceItemForm);
        invoiceItem.setProduct(product);
        invoice.getInvoiceItems().add(invoiceItem);

        InvoiceItemResponse invoiceResponse = new InvoiceItemResponse();
        invoiceResponse.setInvoiceId(invoice.getId());
        invoiceResponse.setInvoiceName(invoice.getName());
        invoiceResponse.setInvoiceStatus(invoice.getInvoiceStatus().getName());

        return invoiceResponse;
    }

}
