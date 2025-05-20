package com.fbaron.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ferney Estupinan Baron
 */
public class CustomerProductModel {

    private Long id;
    private CustomerModel customer;
    private ProductModel product;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdDate;
    private String status;

    public CustomerProductModel() {
    }

    public CustomerProductModel(Long id, CustomerModel customer, ProductModel product, String accountNumber,
                                BigDecimal balance, LocalDateTime createdDate, String status) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdDate = createdDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
