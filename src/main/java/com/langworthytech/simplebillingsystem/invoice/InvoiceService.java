package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.customer.CustomerService;
import com.langworthytech.simplebillingsystem.invoice.dto.InvoiceItemFormModel;
import com.langworthytech.simplebillingsystem.invoice.dto.InvoiceItemResponse;
import com.langworthytech.simplebillingsystem.product.Product;
import com.langworthytech.simplebillingsystem.product.ProductService;
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

    private ProductService productService;

    private CustomerService customerService;

    private InvoiceStatusRepository statusRepository;

    public InvoiceService(
            InvoiceItemRepository invoiceItemRepository,
            InvoiceRepository invoiceRepository,
            ProductService productService,
            CustomerService customerService,
            InvoiceStatusRepository statusRepository
    ) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.statusRepository = statusRepository;
    }

    @Override
    public InvoiceItemResponse createInvoiceItem(InvoiceItemFormModel invoiceItem) {

        Product product;

        if(invoiceItem.getProductId() == null) {
            product = new Product(invoiceItem.getProductName(), invoiceItem.getProductDescription());
            productService.createProduct(product);
        }
        else {
            Optional<Product> optionalProduct = productService.findProductById(invoiceItem.getProductId());
            product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        }

        BigDecimal qty = new BigDecimal(invoiceItem.getItemQuantity());
        BigDecimal amount = invoiceItem.getUnitPrice().multiply(qty);

        InvoiceItem item = new InvoiceItem();
        item.setQuantity(invoiceItem.getItemQuantity());
        item.setAmount(amount);

        item.setProduct(product);

        InvoiceItem savedInvoiceItem = invoiceItemRepository.save(item);

        InvoiceItemResponse invoiceItemResponse = new InvoiceItemResponse();
        invoiceItemResponse.setInvoiceItemId(savedInvoiceItem.getId());
        invoiceItemResponse.setProductName(savedInvoiceItem.getProduct().getName());
        invoiceItemResponse.setProductDescription(savedInvoiceItem.getProduct().getDescription());
        invoiceItemResponse.setQuantity(savedInvoiceItem.getQuantity());
        invoiceItemResponse.setUnitPrice(savedInvoiceItem.getAmount());

        return invoiceItemResponse;
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
        invoiceRepository.save(invoice);

        return invoice;
    }

    @Override
    public Invoice findInvoiceById(Long id) {

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        return optionalInvoice.orElseThrow(() -> new EntityNotFoundException("Invoice not found!"));
    }

    @Override
    public Invoice findInvoiceByName(String name) {

        return null;
    }


}
