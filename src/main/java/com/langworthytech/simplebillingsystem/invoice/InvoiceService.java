package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.account.dto.AccountViewResponse;
import com.langworthytech.simplebillingsystem.customer.Customer;
import com.langworthytech.simplebillingsystem.customer.CustomerRepository;
import com.langworthytech.simplebillingsystem.customer.CustomerService;
import com.langworthytech.simplebillingsystem.customer.dto.CustomerResponse;
import com.langworthytech.simplebillingsystem.invoice.dto.*;
import com.langworthytech.simplebillingsystem.product.Product;
import com.langworthytech.simplebillingsystem.product.ProductService;
import com.langworthytech.simplebillingsystem.security.AuthenticatedUser;
import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import com.langworthytech.simplebillingsystem.security.User;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    
    private ModelMapper modelMapper;

    public InvoiceService(
            InvoiceItemRepository invoiceItemRepository,
            InvoiceRepository invoiceRepository,
            ProductService productService,
            CustomerRepository customerRepository,
            InvoiceStatusRepository statusRepository,
            ModelMapper modelMapper
    ) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceRepository = invoiceRepository;
        this.productService = productService;
        this.customerRepository = customerRepository;
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InvoiceItemResponse createInvoiceItem(InvoiceItemFormModel invoiceItem) {
    	
    	logger.info(invoiceItem.toString());

//        Product product = null;
//
//        if(invoiceItem.getProductId() == null) {
//            product = new Product(invoiceItem.getProductName(), invoiceItem.getProductDescription());
//            productService.createProduct(product);
//        }
//        else {
//            Optional<Product> optionalProduct = productService.findProductById(invoiceItem.getProductId());
//            product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("Product not found!"));
//        }
//
//        BigDecimal itemSubtotal = calculateSubtotal(invoiceItem.getUnitPrice(), invoiceItem.getItemQuantity());
//        
//        BigDecimal taxAmount = new BigDecimal(0);
//        
//        if(invoiceItem.getTaxRate() != null) {
//        	taxAmount = calculateSalesTax(invoiceItem.getTaxRate(), itemSubtotal);
//        }
//        
//        BigDecimal itemTotal = calculateTotal(taxAmount, itemSubtotal);
//        
//        InvoiceItem item = new InvoiceItem();
//        item.setInvoice(invoiceItem.getInvoice());
//        item.setQuantity(invoiceItem.getItemQuantity());
//        item.setUnitPrice(invoiceItem.getUnitPrice());
//        item.setLineSubtotal(itemSubtotal);
//        item.setTaxRate(invoiceItem.getTaxRate());
//        item.setTaxAmount(taxAmount);
//        item.setAmount(itemTotal);
//
//        item.setProduct(product);
//
//        InvoiceItem savedInvoiceItem = invoiceItemRepository.save(item);

        InvoiceItemResponse invoiceItemResponse = new InvoiceItemResponse();
//        invoiceItemResponse.setInvoiceItemId(savedInvoiceItem.getId());
//        invoiceItemResponse.setProductName(savedInvoiceItem.getProduct().getName());
//        invoiceItemResponse.setProductDescription(savedInvoiceItem.getProduct().getDescription());
//        invoiceItemResponse.setUnitPrice(savedInvoiceItem.getAmount());
//        invoiceItemResponse.setLineSubtotal(savedInvoiceItem.getAmount());
//        invoiceItemResponse.setQuantity(savedInvoiceItem.getQuantity());
//        invoiceItemResponse.setTaxRate(savedInvoiceItem.getTaxRate());
//        invoiceItemResponse.setTaxAmount(savedInvoiceItem.getTaxAmount());
//        invoiceItemResponse.setAmount(savedInvoiceItem.getAmount());

        return invoiceItemResponse;
    }

    @Override
    public CreateInvoiceResponse createInvoice(InvoiceFormModel invoiceFormModel, CustomUserDetails userDetails) {

        Account account = userDetails.getUser().getAccount();

        Invoice invoice = createDraftInvoice(userDetails);

        Optional<Customer> optionalCustomer = customerRepository.findById(invoiceFormModel.getCustomerId());
        Customer customer = optionalCustomer.orElseThrow(() -> new EntityNotFoundException("Customer not found!"));
        invoice.setCustomer(customer);

        invoice.setNotes(invoiceFormModel.getInvoiceNote());
        
        CreateInvoiceResponse invoiceResponse = new CreateInvoiceResponse();

        List<InvoiceItemResponse> invoiceItemResponseList = new ArrayList<>();
        invoiceFormModel.getInvoiceItems().forEach(item -> {
        	item.setInvoice(invoice);
        	invoiceItemResponseList.add(createInvoiceItem(item));
        });
        
        BigDecimal invoiceSubtotal = new BigDecimal(0);
        BigDecimal invoiceTax = new BigDecimal(0);
        
        
        invoiceItemResponseList.forEach(item -> {
        	
        	invoiceSubtotal.add(item.getLineSubtotal());
        	invoiceTax.add(item.getTaxAmount());
        	
        });

        BigDecimal invoiceTotal = invoiceSubtotal.add(invoiceTax);

        
        invoice.setSubtotal(invoiceSubtotal);
        invoice.setTax(invoiceTax);
        invoice.setTotal(invoiceTotal);
        
        
        invoiceResponse.setInvoiceItems(invoiceItemResponseList);

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


        logger.info(invoiceResponse.toString());

        return invoiceResponse;
    }

    @Override
    public Invoice createDraftInvoice(CustomUserDetails userDetails) {

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
    public InvoiceViewResponse findInvoiceById(Long id) throws EntityNotFoundException {

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        Invoice invoice = optionalInvoice.orElseThrow(() -> new EntityNotFoundException("Invoice not found!"));

        User user = invoice.getUser();
        
        Account account = user.getAccount();
        AccountViewResponse accountView = new AccountViewResponse(
        		account.getId(),
        		account.getCompany(),
        		account.getAddress(),
        		account.getCity(),
        		account.getState(),
        		account.getZip(),
        		account.getPhone(),
        		account.getEmail(),
        		account.getWebsite()
        );
        
        Customer customer = invoice.getCustomer();
        CustomerResponse customerView = new CustomerResponse(
        		customer.getId(),
        		customer.getFirstName(),
        		customer.getLastName(),
        		customer.getEmail(),
        		customer.getPhone(),
        		customer.getCompanyName()
        );
        
        List<InvoiceItemResponse> invoiceItems = new ArrayList<>();
        invoice.getInvoiceItems().forEach(item -> {
        	InvoiceItemResponse invoiceItemView = new InvoiceItemResponse();
        	invoiceItemView.setInvoiceItemId(item.getId());
        	invoiceItemView.setProductName(item.getProduct().getName());
        	invoiceItemView.setProductDescription(item.getProduct().getDescription());
        	invoiceItemView.setQuantity(item.getQuantity());
        	invoiceItemView.setTaxRate(item.getTaxRate());
        	invoiceItemView.setAmount(item.getAmount());
        	invoiceItems.add(invoiceItemView);
        });

        InvoiceViewResponse invoiceView = new InvoiceViewResponse();
        invoiceView.setInvoiceId(invoice.getId());
        invoiceView.setInvoiceName(invoice.getName());
        invoiceView.setInvoiceNote(invoice.getNotes());
        invoiceView.setInvoiceNumber(invoice.getInvoiceNum());
        invoiceView.setInvoiceStatus(invoice.getInvoiceStatus().getName());
        invoiceView.setInvoiceSubtotal(invoice.getSubtotal());
        invoiceView.setInvoiceTax(invoice.getTax());
        invoiceView.setInvoiceTotal(invoice.getTotal());
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        invoiceView.setInvoiceCreatedAt(invoice.getCreatedAt().format(formatter));
        invoiceView.setInvoiceUpdatedAt(invoice.getUpdatedAt().format(formatter));
        
        invoiceView.setInvoiceItems(invoiceItems);
        invoiceView.setAccount(accountView);
        invoiceView.setCustomer(customerView);
        
        invoiceView.setUserName(user.getFirstName() + " " + user.getLastName());
        
        return invoiceView;
    }

    @Override
    public Invoice findInvoiceByName(String name) {

        return null;
    }

    @Override
    public BigDecimal calculateSubtotal(BigDecimal unitPrice, int quantity) {
    	BigDecimal qty = new BigDecimal(quantity);
        return unitPrice.multiply(qty);
    }

    @Override
    public BigDecimal calculateSalesTax(BigDecimal taxRate, BigDecimal subtotal) {
        
    	BigDecimal taxDecimal = taxRate.divide(BigDecimal.valueOf(100));
    	BigDecimal taxAmount = taxDecimal.multiply(subtotal);
    	return taxAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public BigDecimal calculateTotal(BigDecimal tax, BigDecimal subtotal) {
        return subtotal.add(tax).setScale(2, RoundingMode.HALF_EVEN);
    }


}
