package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.invoice.dto.*;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;

import java.math.BigDecimal;
import java.util.List;

public interface IInvoiceService {

    InvoiceItemResponse createInvoiceItem(InvoiceItemFormModel invoiceItemModel);

    CreateInvoiceResponse createInvoice(InvoiceFormModel invoiceFormModel, CustomUserDetails userDetails);

    Invoice createDraftInvoice();

    List<InvoiceListItemResponse> findAllInvoices();

    InvoiceViewResponse findInvoiceById(Long id);

    Invoice findInvoiceByName(String name);

    BigDecimal calculateSubtotal(BigDecimal unitPrice, int quantity);

    BigDecimal calculateSalesTax(BigDecimal taxRate, BigDecimal subtotal);

    BigDecimal calculateTotal(BigDecimal tax, BigDecimal subtotal);
}
