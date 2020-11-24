package com.DA.store.repository;

import com.DA.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, java.util.UUID> {

    @Override
    Optional<Product> findById(UUID uuid);

}
