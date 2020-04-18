package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class InvoiceFormModel implements Serializable {

    private Long customerId;

    private List<InvoiceItemFormModel> invoiceItems;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<InvoiceItemFormModel> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemFormModel> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    @Override
    public String toString() {
        return "InvoiceFormModel{" +
                "customerId=" + customerId +
                ", invoiceItems=" + invoiceItems +
                '}';
    }
}
