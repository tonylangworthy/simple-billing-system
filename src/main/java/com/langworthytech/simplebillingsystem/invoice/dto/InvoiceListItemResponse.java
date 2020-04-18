package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class InvoiceListItemResponse implements Serializable {

    private Long invoiceId;

    private String invoiceName;

    private int invoiceNumber;

    private String invoiceStatus;

    private String createdAt;

    private String updatedAt;

    private Long customerId;

    private String customerName;

    private Long userId;

    private String userName;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "InvoiceListItemResponse{" +
                "invoiceId=" + invoiceId +
                ", invoiceName='" + invoiceName + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
