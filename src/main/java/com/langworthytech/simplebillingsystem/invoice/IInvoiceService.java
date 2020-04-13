package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.invoice.dto.CreateInvoiceItemRequest;

public interface IInvoiceService {

    InvoiceItem createInvoiceItem(CreateInvoiceItemRequest invoiceItemModel);

    Invoice createInvoice(Invoice invoice);

    Invoice createDraftInvoice();

    Invoice findInvoiceByName(String name);
}
