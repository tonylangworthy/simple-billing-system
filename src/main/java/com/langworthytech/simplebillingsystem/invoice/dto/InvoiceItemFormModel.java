package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import com.langworthytech.simplebillingsystem.invoice.Invoice;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class InvoiceItemFormModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Invoice invoice;

	private Long productId;

    private String productName;

    private String productDescription;

    private String itemQuantity;

    private BigDecimal unitPrice;

    private BigDecimal taxRate;

    public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return "InvoiceItemFormModel{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", unitPrice=" + unitPrice +
                ", taxRate=" + taxRate +
                '}';
    }
}
