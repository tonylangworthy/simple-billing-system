package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class InvoiceItemContainer implements Serializable {

    private List<InvoiceItemFormModel> invoiceItems;

    public List<InvoiceItemFormModel> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemFormModel> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}
