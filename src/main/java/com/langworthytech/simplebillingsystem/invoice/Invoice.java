package com.langworthytech.simplebillingsystem.invoice;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.customer.Customer;
import com.langworthytech.simplebillingsystem.security.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(generator = "invoice-number")
    @GenericGenerator(
            name = "invoice-number",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "initial_value", value = "1")
            }
    )
    private int invoiceNum;

    @Column(length = 50)
    private String name;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "invoice")
    private Set<InvoiceItem> invoiceItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "invoice_status_id", nullable = false)
    private InvoiceStatus invoiceStatus;

    @Lob
    private String notes;

    private BigDecimal subtotal;
    
    private BigDecimal tax;
    
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Invoice() {}

    public Invoice(String name, InvoiceStatus invoiceStatus) {
        this.name = name;
        this.invoiceStatus = invoiceStatus;
    }
}
