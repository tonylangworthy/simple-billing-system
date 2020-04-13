package com.langworthytech.simplebillingsystem.billing;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "intervals")
public class Interval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "interval")
    private List<Plan> plans = new ArrayList<>();
}
