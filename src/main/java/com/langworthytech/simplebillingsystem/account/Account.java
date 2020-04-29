package com.langworthytech.simplebillingsystem.account;

import com.langworthytech.simplebillingsystem.customer.Customer;
import com.langworthytech.simplebillingsystem.invoice.Invoice;
import com.langworthytech.simplebillingsystem.security.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String phone;

    @Column(unique = true)
    private String email;

    private String website;

    private boolean active;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "account")
    private Set<User> users = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "account")
    private List<Customer> customers = new ArrayList<>();

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
}
