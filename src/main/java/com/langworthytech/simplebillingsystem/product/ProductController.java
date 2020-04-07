package com.langworthytech.simplebillingsystem.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String showAllProducts(Model model) {
        Iterable<Product> iterable = productService.findAllProducts();
        model.addAttribute("products", iterable);
        return "product_list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductFormModel());
        return "product_create";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid
            @ModelAttribute("product") ProductFormModel formModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if(bindingResult.hasErrors()) {

        }
        productService.createProductFromModel(formModel);


        return "redirect:/products/create";
    }
}
