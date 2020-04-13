package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.product.Product;
import com.langworthytech.simplebillingsystem.product.ProductFormModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InvoiceItemFormModel {

    private Long invoiceItemId;

    private Long productId;

    private Long customerId;

    private String productName;

    private String productDescription;

    private String productSku;

    private int quantity = 1;

    private BigDecimal unitPrice;

    public InvoiceItemFormModel() {}

    public InvoiceItemFormModel(Long id, String productName, String productDescription, String productSku) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productSku = productSku;
    }

    public Long getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Long invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "InvoiceItemFormModel{" +
                "invoiceItemId=" + invoiceItemId +
                ", productId=" + productId +
                ", customerId=" + customerId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productSku='" + productSku + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
