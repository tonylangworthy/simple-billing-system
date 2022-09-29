package com.langworthytech.simplebillingsystem.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;


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

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
