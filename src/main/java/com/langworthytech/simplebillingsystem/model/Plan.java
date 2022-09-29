package com.langworthytech.simplebillingsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "interval_id", nullable = false)
    private Interval interval;

    @ManyToMany(mappedBy = "plans")
    private Set<Subscription> subscriptions;

    @ManyToMany(mappedBy = "plans")
    private Set<Product> products;

}
