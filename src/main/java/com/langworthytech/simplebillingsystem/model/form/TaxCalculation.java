package com.langworthytech.simplebillingsystem.model.form;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class TaxCalculation implements Serializable {

    private BigDecimal taxRate;

    private BigDecimal subtotal;

    private BigDecimal taxAmount;

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public String toString() {
        return "TaxCalculation{" +
                "taxRate=" + taxRate +
                ", subtotal=" + subtotal +
                ", taxAmount=" + taxAmount +
                '}';
    }
}
