package com.langworthytech.simplebillingsystem.product;

public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String sku;

    private boolean service;

    public ProductDto(Long id, String name, String description, String sku, boolean service) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.service = service;
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

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }
}
