package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
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
    
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
