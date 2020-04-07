package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNum;

    @OneToMany(mappedBy = "invoice")
    private Set<InvoiceItem> invoiceItems = new HashSet<>();

    @Lob
    private String notes;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

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

}
