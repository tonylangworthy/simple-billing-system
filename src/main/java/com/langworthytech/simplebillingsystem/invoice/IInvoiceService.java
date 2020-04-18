package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.invoice.dto.*;

import java.util.List;

public interface IInvoiceService {

    InvoiceItemResponse createInvoiceItem(InvoiceItemFormModel invoiceItemModel);

    CreateInvoiceResponse createInvoice(InvoiceFormModel invoiceFormModel);

    Invoice createDraftInvoice();

    List<InvoiceListItemResponse> findAllInvoices();

    CreateInvoiceResponse findInvoiceById(Long id);

    Invoice findInvoiceByName(String name);
}
