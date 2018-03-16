package ua.com.owu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.ProductDAO;
import ua.com.owu.entity.Product;
import ua.com.owu.services.ProductService;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public void save(String title) {
        Product product = Product.builder().title(title).build();
        productDAO.save(product);
    }

    @Override
    public void save(Product product) {
        productDAO.save(product);

    }

    @Override
    public Product findOne(int id) {
        return productDAO.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }



}
