package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.invoice.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface IInvoiceService {

    InvoiceItemResponse createInvoiceItem(InvoiceItemFormModel invoiceItemModel);

    CreateInvoiceResponse createInvoice(InvoiceFormModel invoiceFormModel);

    Invoice createDraftInvoice();

    List<InvoiceListItemResponse> findAllInvoices();

    InvoiceViewResponse findInvoiceById(Long id);

    Invoice findInvoiceByName(String name);

    BigDecimal calculateLineTotal(BigDecimal unitPrice, int quantity);

    BigDecimal calculateSubtotal(BigDecimal invoiceItemTotal);

    BigDecimal calculateSalesTax(BigDecimal taxRate);

    BigDecimal calculateTotal(BigDecimal taxRate);
}
