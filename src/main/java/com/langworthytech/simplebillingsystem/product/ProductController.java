package com.langworthytech.simplebillingsystem.product;

import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import com.langworthytech.simplebillingsystem.security.IAuthenticationFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private IAuthenticationFacade authenticationFacade;

    private IProductService productService;

    public ProductController(AuthenticationFacade authenticationFacade, ProductService productService) {
        this.authenticationFacade = authenticationFacade;
        this.productService = productService;
    }

    @GetMapping("")
    public String showProducts(Model model) {

        CustomUserDetails userDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();
        model.addAttribute("userName", userDetails.getFirstName() + " " + userDetails.getLastName());

        List<ProductFormModel> productList = new ArrayList<>();

        productService.findAllProducts().forEach(product -> {

            productList.add(new ProductFormModel(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getSku(),
                    product.isService(),
                    product.getCreatedAt(),
                    product.getUpdatedAt()
            ));
        });
        model.addAttribute("products", productList);
        return "product_list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductFormModel());
        return "product_create";
    }

    @PostMapping("")
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

    @GetMapping("/autocomplete/{term}")
    public @ResponseBody List<ProductDto> searchProductByNameStartsWith(@PathVariable String term) {
        List<ProductDto> productList = new ArrayList<>();

        productService.searchProductsStartsWith(term).forEach(product -> {
            productList.add(new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getSku(),
                    product.isService()
            ));
        });
        return productList;
    }
}
