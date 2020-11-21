package com.DA.store.service.impl;

import com.DA.store.controller.exeption.ProductAlreadyExistException;
import com.DA.store.controller.exeption.ProductNotExistException;
import com.DA.store.model.Product;
import com.DA.store.repository.ProductRepository;
import com.DA.store.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductServiceIpl implements ProductService {
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceIpl.class);

    private ProductRepository productRepository;

    @Autowired
    public void setRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getByName(String name) {
        LOGGER.debug("Get  Products by name");
        return productRepository.findAll().stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getAll() {
        LOGGER.debug("Get all  Products");
        return productRepository.findAll();
    }

    @Override
    public Product getById(UUID id) {
        LOGGER.debug("Get Products by id");
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotExistException(String.format("Product with id = %s not found" + id)));
    }

    @Override
    public void save(Product entity) {
        LOGGER.debug("Save Products");
        if (getByName(entity.getName()).stream().anyMatch(p -> p.getManufacturer()
                .equals(entity.getManufacturer()))) {
            throw new ProductAlreadyExistException("Product already exist" + entity.getName());
        }
        productRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        LOGGER.debug("Save Products");
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product entity) {
        LOGGER.debug("Save Products");
        return productRepository.save(entity);
    }

}
