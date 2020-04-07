package com.langworthytech.simplebillingsystem.product;

import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductBySku(String sku) {
        return null;
    }

    public Product createProductFromModel(ProductFormModel productForm) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setSku(generateSku());
        product.setAccount(userDetails.getAccount());

        return productRepository.save(product);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Iterable<Product> findAllProducts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        return productRepository.findAllByAccount(userDetails.getAccount());
    }

    private String generateSku() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHms");
        return now.format(formatter);
    }
}
