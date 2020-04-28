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
    
    @Column(precision = 19, scale = 4)
    private BigDecimal taxRate;
    
    private BigDecimal taxAmount;

    // unit_price * quantity
    @Column(nullable = false)
    private BigDecimal amount;
    
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
