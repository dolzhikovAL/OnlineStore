package com.DA.store.service;

import com.DA.store.model.Product;

public interface ProductService extends BaseService<Product> {
    Product getByName (String name);
}
