package net.springapp.service;

import net.springapp.model.Product;
import net.springapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findOne(Long.parseLong(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        productRepository.delete(Long.parseLong(id));
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
}
