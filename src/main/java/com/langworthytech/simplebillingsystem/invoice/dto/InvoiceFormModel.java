package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceFormModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerId;
	
    private String invoiceNote;
    
    private String taxTotal;

    private List<InvoiceItemFormModel> invoiceItems = new ArrayList<>();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

	public String getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceNote(String invoiceNote) {
        this.invoiceNote = invoiceNote;
    }
    
	public List<InvoiceItemFormModel> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemFormModel> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public String getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(String taxTotal) {
        this.taxTotal = taxTotal;
    }

    @Override
    public String toString() {
        return "InvoiceFormModel{" +
                "customerId=" + customerId +
                ", invoiceNote='" + invoiceNote + '\'' +
                ", taxTotal=" + taxTotal +
                ", invoiceItems=" + invoiceItems +
                '}';
    }
}
