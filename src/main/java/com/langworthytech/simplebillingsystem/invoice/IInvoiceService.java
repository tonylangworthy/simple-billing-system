package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.invoice.dto.InvoiceItemFormModel;
import com.langworthytech.simplebillingsystem.invoice.dto.InvoiceItemResponse;

public interface IInvoiceService {

    InvoiceItemResponse createInvoiceItem(InvoiceItemFormModel invoiceItemModel);

    Invoice createInvoice(Invoice invoice);

    Invoice createDraftInvoice();

    Invoice findInvoiceById(Long id);

    Invoice findInvoiceByName(String name);
}
