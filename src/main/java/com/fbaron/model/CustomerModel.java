package com.fbaron.model;

import java.util.List;

/**
 * @author Ferney Estupinan Baron
 */
public class CustomerModel {

    private Long id;
    private UserModel user;
    private String email;
    private String phone;
    private String address;
    private String status;


    private List<CustomerProductModel> products;


    public CustomerModel() {
    }

    public CustomerModel(Long id, UserModel user, String email, String phone, String address, String status) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomerProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<CustomerProductModel> products) {
        this.products = products;
    }
}
