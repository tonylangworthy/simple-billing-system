package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class InvoiceResponse implements Serializable {

    private Long invoiceId;

    private String invoiceName;

    private String invoiceNumber;

    private String invoiceStatus;

    private String notes;

    private String createdAt;

    private String updatedAt;

}
