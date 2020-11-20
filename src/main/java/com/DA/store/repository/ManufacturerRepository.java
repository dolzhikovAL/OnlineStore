package com.DA.store.repository;

import com.DA.store.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,java.util.UUID> {
   Optional<Manufacturer> findByName (String name);
}
