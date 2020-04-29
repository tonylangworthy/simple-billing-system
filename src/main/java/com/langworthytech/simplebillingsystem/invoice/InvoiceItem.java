package com.langworthytech.simplebillingsystem.invoice;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.product.Product;
import com.langworthytech.simplebillingsystem.security.Role;
import com.langworthytech.simplebillingsystem.security.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "invoice_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    
    private BigDecimal unitPrice;
    
    // unitPrice * quantity
    private BigDecimal lineSubtotal;
    
    @Column(precision = 19, scale = 4)
    private BigDecimal taxRate;
    
    // lineSubtotal * (taxRate / 100)
    private BigDecimal taxAmount;

    // unitPrice * quantity + taxAmount
    @Column(nullable = false)
    private BigDecimal amount;
    
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
