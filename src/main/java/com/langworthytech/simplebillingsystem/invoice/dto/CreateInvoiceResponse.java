package com.langworthytech.simplebillingsystem.invoice.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateInvoiceResponse implements Serializable {

    private Long invoiceId;

    private String invoiceName;

    private String invoiceNote;

    private int invoiceNumber;

    private String invoiceStatus;

    private String invoiceCreatedAt;

    private String invoiceUpdatedAt;

    private List<InvoiceItemResponse> invoiceItems = new ArrayList<>();

    private String accountCompany;

    private String accountAddress;

    private String accountCity;

    private String accountState;

    private String accountZip;

    private String accountPhone;

    private String accountEmail;

    private String accountWebsite;

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

    public String getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceNote(String invoiceNote) {
        this.invoiceNote = invoiceNote;
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

    public String getInvoiceCreatedAt() {
        return invoiceCreatedAt;
    }

    public void setInvoiceCreatedAt(String invoiceCreatedAt) {
        this.invoiceCreatedAt = invoiceCreatedAt;
    }

    public String getInvoiceUpdatedAt() {
        return invoiceUpdatedAt;
    }

    public void setInvoiceUpdatedAt(String invoiceUpdatedAt) {
        this.invoiceUpdatedAt = invoiceUpdatedAt;
    }

    public List<InvoiceItemResponse> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemResponse> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public String getAccountCompany() {
        return accountCompany;
    }

    public void setAccountCompany(String accountCompany) {
        this.accountCompany = accountCompany;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public String getAccountCity() {
        return accountCity;
    }

    public void setAccountCity(String accountCity) {
        this.accountCity = accountCity;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getAccountZip() {
        return accountZip;
    }

    public void setAccountZip(String accountZip) {
        this.accountZip = accountZip;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountWebsite() {
        return accountWebsite;
    }

    public void setAccountWebsite(String accountWebsite) {
        this.accountWebsite = accountWebsite;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CreateInvoiceResponse{" +
                "invoiceId=" + invoiceId +
                ", invoiceName='" + invoiceName + '\'' +
                ", invoiceNote='" + invoiceNote + '\'' +
                ", invoiceNumber=" + invoiceNumber +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", invoiceCreatedAt='" + invoiceCreatedAt + '\'' +
                ", invoiceUpdatedAt='" + invoiceUpdatedAt + '\'' +
                ", invoiceItems=" + invoiceItems +
                ", accountCompany='" + accountCompany + '\'' +
                ", accountAddress='" + accountAddress + '\'' +
                ", accountCity='" + accountCity + '\'' +
                ", accountState='" + accountState + '\'' +
                ", accountZip='" + accountZip + '\'' +
                ", accountPhone='" + accountPhone + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountWebsite='" + accountWebsite + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
