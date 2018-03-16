package ua.com.owu.services;

import ua.com.owu.entity.Product;

import java.util.List;

public interface ProductService {

    void save(String title);

    void save(Product product);

    Product findOne(int id);

    List<Product> findAll();

    void delete(int id);
}
