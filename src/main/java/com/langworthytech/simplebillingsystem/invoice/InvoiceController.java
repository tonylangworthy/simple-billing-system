package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.product.IProductService;
import com.langworthytech.simplebillingsystem.product.ProductFormModel;
import com.langworthytech.simplebillingsystem.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private IProductService productService;

    public InvoiceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        List<InvoiceItemFormModel> invoiceItems = new ArrayList<>();
        productService.findAllProducts().forEach(product -> {
            InvoiceItemFormModel invoice = new InvoiceItemFormModel();
            invoice.setId(product.getId());
            invoice.setProductName(product.getName());
            invoice.setProductDescription(product.getDescription());
            invoice.setProductSku(product.getSku());
            invoiceItems.add(invoice);
        });

        model.addAttribute("invoice", new InvoiceFormModel(invoiceItems));
        return "invoice_create";
    }
}
