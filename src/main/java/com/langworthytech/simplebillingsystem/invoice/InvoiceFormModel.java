package com.langworthytech.simplebillingsystem.invoice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceFormModel {

    private List<InvoiceItemFormModel> invoiceItems;

    public List<InvoiceItemFormModel> getInvoiceItems() {
        return invoiceItems;
    }

    public InvoiceFormModel(List<InvoiceItemFormModel> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemFormModel> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}
