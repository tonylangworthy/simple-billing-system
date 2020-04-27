package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class InvoiceItemResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productName;

    private String productDescription;

    private Long invoiceItemId;

    private int quantity;

    private BigDecimal unitPrice;
    
    private BigDecimal taxRate;
    
    private BigDecimal taxAmount;

    private BigDecimal amount;
    
    public InvoiceItemResponse() {}
    
    public InvoiceItemResponse(
    		Long invoiceItemId, 
    		String productName, 
    		String productDescription, 
    		int quantity, 
    		BigDecimal unitPrice,
    		BigDecimal taxRate,
    		BigDecimal taxAmount,
    		BigDecimal amount
    ) {
    	this.invoiceItemId = invoiceItemId;
    	this.productName = productName;
    	this.productDescription = productDescription;
    	this.quantity = quantity;
    	this.unitPrice = unitPrice;
    	this.taxRate = taxRate;
    	this.taxAmount = taxAmount;
    	this.amount = amount;
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

    public Long getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(Long invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
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
    
    public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
