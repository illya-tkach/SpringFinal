package net.springapp.service;

import net.springapp.model.Product;

import java.util.List;

public interface ProductService {
    void save (Product product);

    Product findById (String id);

    List<Product> getAllProducts();

    void deleteById (String id);

    Product findByName(String name);
}
