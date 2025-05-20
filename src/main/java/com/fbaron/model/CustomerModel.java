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
}
