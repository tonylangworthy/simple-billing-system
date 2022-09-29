package com.langworthytech.simplebillingsystem.dto.response;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceViewResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long invoiceId;

    private String invoiceName;

    private String invoiceNote;

    private int invoiceNumber;

    private String invoiceStatus;

    private BigDecimal invoiceSubtotal;

    private BigDecimal invoiceTax;

    private BigDecimal invoiceTotal;

    private String invoiceCreatedAt;

    private String invoiceUpdatedAt;

    private List<InvoiceItemResponse> invoiceItems = new ArrayList<>();

    private AccountViewResponse account;

    private CustomerResponse customer;

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

    public BigDecimal getInvoiceSubtotal() {
        return invoiceSubtotal;
    }

    public void setInvoiceSubtotal(BigDecimal invoiceSubtotal) {
        this.invoiceSubtotal = invoiceSubtotal;
    }

    public BigDecimal getInvoiceTax() {
        return invoiceTax;
    }

    public void setInvoiceTax(BigDecimal invoiceTax) {
        this.invoiceTax = invoiceTax;
    }

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
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

    public AccountViewResponse getAccount() {
        return account;
    }

    public void setAccount(AccountViewResponse account) {
        this.account = account;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "InvoiceViewResponse{" +
                "invoiceId=" + invoiceId +
                ", invoiceName='" + invoiceName + '\'' +
                ", invoiceNote='" + invoiceNote + '\'' +
                ", invoiceNumber=" + invoiceNumber +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", invoiceSubtotal=" + invoiceSubtotal +
                ", invoiceTax=" + invoiceTax +
                ", invoiceTotal=" + invoiceTotal +
                ", invoiceCreatedAt='" + invoiceCreatedAt + '\'' +
                ", invoiceUpdatedAt='" + invoiceUpdatedAt + '\'' +
                ", invoiceItems=" + invoiceItems +
                ", account=" + account +
                ", customer=" + customer +
                ", userName='" + userName + '\'' +
                '}';
    }
}
