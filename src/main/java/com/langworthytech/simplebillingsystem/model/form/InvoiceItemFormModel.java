package com.langworthytech.simplebillingsystem.model.form;

import org.springframework.stereotype.Component;

import com.langworthytech.simplebillingsystem.model.Invoice;

import java.io.Serializable;

@Component
public class InvoiceItemFormModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Invoice invoice;

	private String productId;

    private String productName;

    private String productDescription;

    private String itemQuantity;

    private String unitPrice;

    private String taxRate;

    public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
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
