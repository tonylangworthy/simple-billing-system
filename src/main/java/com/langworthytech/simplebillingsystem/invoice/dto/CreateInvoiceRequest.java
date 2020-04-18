package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class CreateInvoiceRequest implements Serializable {

    private InvoiceFormModel invoice;

    public CreateInvoiceRequest() {}

    public InvoiceFormModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceFormModel invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "CreateInvoiceRequest{" +
                "invoice=" + invoice +
                '}';
    }
}
