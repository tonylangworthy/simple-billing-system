package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.product.Product;
import com.langworthytech.simplebillingsystem.product.ProductFormModel;
import org.springframework.stereotype.Component;

@Component
public class InvoiceItemFormModel {

    private Long id;

    private String productName;

    private String productDescription;

    private String productSku;

    private int quantity = 1;

    public InvoiceItemFormModel() {}

    public InvoiceItemFormModel(Long id, String productName, String productDescription, String productSku) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productSku = productSku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
