package com.langworthytech.simplebillingsystem.product;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.billing.Plan;
import com.langworthytech.simplebillingsystem.invoice.InvoiceItem;
import com.langworthytech.simplebillingsystem.security.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

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
    private boolean isService;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "product")
    private InvoiceItem invoiceItem;

    @ManyToMany
    @JoinTable(
            name = "products_plans",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "plan_id")}
    )
    private Set<Plan> plans;

    public Product() {}

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
