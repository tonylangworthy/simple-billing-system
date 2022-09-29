package com.langworthytech.simplebillingsystem.service;

import com.langworthytech.simplebillingsystem.model.Product;
import com.langworthytech.simplebillingsystem.model.form.ProductFormModel;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<Product> findProductBySku(String sku);

    Optional<Product> findProductById(Long id);

    List<Product> searchProductsStartsWith(String searchTerm);

    Iterable<Product> findAllProducts();

    Product createProductFromModel(ProductFormModel productForm);

    Product createProduct(Product product);

}
