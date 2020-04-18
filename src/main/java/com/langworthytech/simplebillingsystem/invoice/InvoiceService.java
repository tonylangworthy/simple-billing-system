package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.customer.Customer;
import com.langworthytech.simplebillingsystem.customer.CustomerRepository;
import com.langworthytech.simplebillingsystem.customer.CustomerService;
import com.langworthytech.simplebillingsystem.invoice.dto.*;
import com.langworthytech.simplebillingsystem.product.Product;
import com.langworthytech.simplebillingsystem.product.ProductService;
import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    private InvoiceItemRepository invoiceItemRepository;

    private InvoiceRepository invoiceRepository;

    private ProductService productService;

    private CustomerRepository customerRepository;

    private InvoiceStatusRepository statusRepository;

    public InvoiceService(
            InvoiceItemRepository invoiceItemRepository,
            InvoiceRepository invoiceRepository,
            ProductService productService,
            CustomerRepository customerRepository,
            InvoiceStatusRepository statusRepository
    ) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
        this.customerRepository = customerRepository;
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
    public CreateInvoiceResponse createInvoice(InvoiceFormModel invoiceFormModel) {

        AuthenticationFacade authenticationFacade = new AuthenticationFacade();
        CustomUserDetails userDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();
        Account account = userDetails.getUser().getAccount();

        Invoice invoice = createDraftInvoice();

        Optional<Customer> optionalCustomer = customerRepository.findById(invoiceFormModel.getCustomerId());
        Customer customer = optionalCustomer.orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
        invoice.setCustomer(customer);

        invoice.setNotes(invoiceFormModel.getInvoiceNote());

        CreateInvoiceResponse invoiceResponse = new CreateInvoiceResponse();
        invoiceResponse.setAccountCompany(account.getCompany());
        invoiceResponse.setAccountAddress(account.getAddress());
        invoiceResponse.setAccountCity(account.getCity());
        invoiceResponse.setAccountState(account.getState());
        invoiceResponse.setAccountZip(account.getZip());
        invoiceResponse.setAccountEmail(account.getEmail());
        invoiceResponse.setAccountPhone(account.getPhone());
        invoiceResponse.setAccountWebsite(account.getWebsite());

        invoiceResponse.setInvoiceId(invoice.getId());
        invoiceResponse.setInvoiceName(invoice.getName());
        invoiceResponse.setInvoiceNumber(invoice.getInvoiceNum());
        invoiceResponse.setInvoiceStatus(invoice.getInvoiceStatus().getName());
        invoiceResponse.setInvoiceNote(invoice.getNotes());

        LocalDateTime createdAt = invoice.getCreatedAt();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:m a");
        invoiceResponse.setInvoiceCreatedAt(createdAt.format(formatter));
        invoiceResponse.setInvoiceUpdatedAt(createdAt.format(formatter));


        invoiceFormModel.getInvoiceItems().forEach(item -> {

            invoiceResponse.getInvoiceItems().add(createInvoiceItem(item));
        });

        logger.info(invoiceResponse.toString());

        return invoiceResponse;
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
    public List<InvoiceListItemResponse> findAllInvoices() {

        Iterable<Invoice> invoices = invoiceRepository.findAll();

        List<InvoiceListItemResponse> invoiceListItems = new ArrayList<>();

        invoices.forEach(invoice -> {
            InvoiceListItemResponse listItem = new InvoiceListItemResponse();
            listItem.setInvoiceId(invoice.getId());
            listItem.setInvoiceName(invoice.getName());
            listItem.setInvoiceNumber(invoice.getInvoiceNum());
            listItem.setInvoiceStatus(invoice.getInvoiceStatus().getName());

            listItem.setCustomerId(invoice.getCustomer().getId());
            String customerName = invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getLastName();
            listItem.setCustomerName(customerName);

            listItem.setUserId(invoice.getUser().getId());
            String userName = invoice.getUser().getFirstName() + " " + invoice.getUser().getLastName();
            listItem.setUserName(userName);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            listItem.setCreatedAt(invoice.getCreatedAt().format(formatter));

            invoiceListItems.add(listItem);
        });

        return invoiceListItems;
    }

    @Override
    public CreateInvoiceResponse findInvoiceById(Long id) {

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        Invoice invoice = optionalInvoice.orElseThrow(() -> new EntityNotFoundException("Invoice not found!"));

        CreateInvoiceResponse invoiceResponse = new CreateInvoiceResponse();

        return invoiceResponse;
    }

    @Override
    public Invoice findInvoiceByName(String name) {

        return null;
    }


}
