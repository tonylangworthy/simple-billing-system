package com.langworthytech.simplebillingsystem.product;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ProductFormModel {

    private Long id;

    private String name;

    private String description;

    private String sku;

    private boolean isService;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductFormModel() {

    }

    public ProductFormModel(
            Long id,
            String name,
            String description,
            String sku,
            boolean isService,
            LocalDateTime createAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.isService = isService;
        this.createdAt = createAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean getIsService() {
        return isService;
    }

    public void setIsService(boolean service) {
        isService = service;
    }

    public void setCreatedAt(LocalDateTime date) {
        this.createdAt = date;
    }

    public void setUpdatedAt(LocalDateTime date) {
        this.updatedAt = date;
    }

    public String getSlashedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        return formatter.format(this.createdAt);
    }

    public String getSlashedUpdatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return formatter.format(this.updatedAt);
    }

    public String getFriendlyCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        return formatter.format(this.createdAt);
    }

    public String getFriendlyUpdatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return formatter.format(this.updatedAt);
    }
}
