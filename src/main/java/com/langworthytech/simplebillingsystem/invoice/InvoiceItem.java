package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.product.Product;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoice_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;
}
