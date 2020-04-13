package com.langworthytech.simplebillingsystem.billing;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
