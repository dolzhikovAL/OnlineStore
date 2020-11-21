package com.DA.store.service;

import com.DA.store.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    List<Product> getByName (String name);
}
