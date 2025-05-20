package com.fbaron.dao;

import com.fbaron.model.ProductModel;

import java.util.List;

/**
 * @author Ferney Estupinan Baron
 */
public interface ProductDAO {

    void insertProduct(ProductModel model);

    ProductModel getProductById(long id);

    List<ProductModel> getAllProducts();

}
