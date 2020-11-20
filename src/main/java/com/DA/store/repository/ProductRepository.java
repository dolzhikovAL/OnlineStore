package com.DA.store.repository;

import com.DA.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,java.util.UUID> {
    Product findByName (String name);

}
