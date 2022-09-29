package com.langworthytech.simplebillingsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    private int intervalCount;

    private boolean trial;

    private LocalDateTime trialBeginDate;

    private LocalDateTime trialEndDate;

    private int trialPeriodDays = 0;

    private LocalDateTime cancelPeriod;

    private LocalDateTime currentPeriodStart;

    private LocalDateTime currentPeriodEnd;

    @ManyToMany
    @JoinTable(
            name = "subscriptions_plans",
            joinColumns = {@JoinColumn(name = "subscription_id")},
            inverseJoinColumns = {@JoinColumn(name = "plan_id")}
    )
    private Set<Plan> plans = new HashSet<>();
}
