package com.langworthytech.simplebillingsystem.product;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.invoice.InvoiceItem;
import com.langworthytech.simplebillingsystem.security.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(length = 20, nullable = false)
    private String sku;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean isSubscription;

    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToOne(mappedBy = "product")
    private InvoiceItem invoiceItem;
}
