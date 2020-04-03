package com.langworthytech.simplebillingsystem.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    @ManyToOne
    private CustomUser customUser;
}
