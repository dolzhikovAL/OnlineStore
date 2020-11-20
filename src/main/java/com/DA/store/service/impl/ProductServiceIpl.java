package com.DA.store.service.impl;

import com.DA.store.model.Product;
import com.DA.store.repository.ProductRepository;
import com.DA.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ProductServiceIpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(UUID id) {
        return null;
    }

    @Override
    public void save(Product entity) {

    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public Product getByName(String name) {
        return null;
    }
}
