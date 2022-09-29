package com.langworthytech.simplebillingsystem.model;

import com.langworthytech.simplebillingsystem.security.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "account")
    private Set<User> users = new HashSet<>();

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

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Account) {
            if (((Account) obj).getEmail().equals(getEmail())) {
                return true;
            }
        }
        return false;
    }

}
