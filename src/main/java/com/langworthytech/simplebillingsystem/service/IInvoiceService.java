package com.langworthytech.simplebillingsystem.service;

import com.langworthytech.simplebillingsystem.dto.response.CreateInvoiceResponse;
import com.langworthytech.simplebillingsystem.dto.response.InvoiceItemResponse;
import com.langworthytech.simplebillingsystem.dto.response.InvoiceListItemResponse;
import com.langworthytech.simplebillingsystem.dto.response.InvoiceViewResponse;
import com.langworthytech.simplebillingsystem.model.Invoice;
import com.langworthytech.simplebillingsystem.model.form.InvoiceFormModel;
import com.langworthytech.simplebillingsystem.model.form.InvoiceItemFormModel;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;

import java.math.BigDecimal;
import java.util.List;

public interface IInvoiceService {

    InvoiceItemResponse createInvoiceItem(InvoiceItemFormModel invoiceItemModel);

    CreateInvoiceResponse createInvoice(InvoiceFormModel invoiceFormModel, CustomUserDetails userDetails);

    Invoice createDraftInvoice(CustomUserDetails userDetails);

    List<InvoiceListItemResponse> findAllInvoices();

    InvoiceViewResponse findInvoiceById(Long id);

    Invoice findInvoiceByName(String name);

    BigDecimal calculateSubtotal(BigDecimal unitPrice, int quantity);

    BigDecimal calculateSalesTax(BigDecimal taxRate, BigDecimal subtotal);

    BigDecimal calculateTotal(BigDecimal tax, BigDecimal subtotal);
}
