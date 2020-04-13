package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.invoice.dto.CreateInvoiceItemRequest;
import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {

    private InvoiceItemRepository invoiceItemRepository;

    private InvoiceRepository invoiceRepository;

    private InvoiceStatusRepository statusRepository;

    public InvoiceService(
            InvoiceItemRepository invoiceItemRepository,
            InvoiceRepository invoiceRepository,
            InvoiceStatusRepository statusRepository
    ) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceRepository = invoiceRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public InvoiceItem createInvoiceItem(CreateInvoiceItemRequest invoiceItem) {

        BigDecimal qty = new BigDecimal(invoiceItem.getQuantity());
        BigDecimal amount = invoiceItem.getUnitPrice().multiply(qty);

        InvoiceItem item = new InvoiceItem();
        item.setQuantity(invoiceItem.getQuantity());
        item.setAmount(amount);

        return invoiceItemRepository.save(item);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice createDraftInvoice() {

        AuthenticationFacade authenticationFacade = new AuthenticationFacade();
        CustomUserDetails userDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();

        LocalDateTime dateTime = LocalDateTime.now();
        String createdDate = dateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy h:m a"));


        String draftName = "Draft [created " + createdDate + "]";

        Optional<InvoiceStatus> optionalStatus = statusRepository.findByName("DRAFT");
        InvoiceStatus status = optionalStatus.orElseThrow(() -> new EntityNotFoundException("Invoice Status not found!"));
        Invoice invoice = new Invoice(draftName, status);
        invoice.setUser(userDetails.getUser());

        return invoice;
    }

    @Override
    public Invoice findInvoiceByName(String name) {

        return null;
    }


}
