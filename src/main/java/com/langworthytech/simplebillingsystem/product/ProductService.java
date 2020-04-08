package com.langworthytech.simplebillingsystem.product;

import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import com.langworthytech.simplebillingsystem.security.IAuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private IAuthenticationFacade authenticationFacade;

    private ProductRepository productRepository;

    @Autowired
    public ProductService(AuthenticationFacade authenticationFacade, ProductRepository productRepository) {
        this.authenticationFacade = authenticationFacade;
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductBySku(String sku) {
        return null;
    }

    public Product createProductFromModel(ProductFormModel productForm) {

        logger.info("getAuthentication().getName(): " + authenticationFacade.getAuthentication().getName());

        CustomUserDetails customUserDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();

//        logger.info("user id: " + customUserDetails.g);

        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setSku(generateSku());
        product.setUser(customUserDetails.getUser());

        return productRepository.save(product);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Iterable<Product> findAllProducts() {

        CustomUserDetails userDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();

        return productRepository.findAllByAccount(userDetails.getUser().getAccount());
    }

    private String generateSku() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHms");
        return now.format(formatter);
    }
}
