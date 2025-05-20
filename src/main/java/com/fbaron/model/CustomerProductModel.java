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

}
