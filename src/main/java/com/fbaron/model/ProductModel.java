package com.fbaron.model;

import java.math.BigDecimal;

/**
 * @author Ferney Estupinan Baron
 */
public class ProductModel {

    private Long id;
    private String name;
    private String description;
    private BigDecimal interestRate;

    public ProductModel() {
    }

    public ProductModel(Long id, String name, String description, BigDecimal interestRate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
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

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

}
