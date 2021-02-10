package com.cybertek.service;

import com.cybertek.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    List<Product> delete(Integer id);
    List<Product> update(Integer id, Product product);
    List<Product> create(Product product);
    Product getProduct(Integer id);
}
