package com.langworthytech.simplebillingsystem.billing;

import org.springframework.stereotype.Component;

@Component
public class BillingCycleFormModel {

    private Long id;

    private String intervalName;

    private int intervalCount;

    public BillingCycleFormModel() {}

    public BillingCycleFormModel(Long id, String intervalName, int intervalCount) {
        this.id = id;
        this.intervalName = intervalName;
        this.intervalCount = intervalCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntervalName() {
        return intervalName;
    }

    public void setIntervalName(String intervalName) {
        this.intervalName = intervalName;
    }

    public int getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(int intervalCount) {
        this.intervalCount = intervalCount;
    }
}
