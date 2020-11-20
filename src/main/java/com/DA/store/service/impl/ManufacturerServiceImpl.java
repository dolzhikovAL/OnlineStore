package com.DA.store.service.impl;

import com.DA.store.controller.exeption.ManufacturerAlreadyExistException;
import com.DA.store.controller.exeption.ManufacturerNotExistException;
import com.DA.store.model.Manufacturer;
import com.DA.store.repository.ManufacturerRepository;
import com.DA.store.service.ManufacturerService;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public @Data
class ManufacturerServiceImpl implements ManufacturerService {

    private static final Logger LOGGER= LogManager.getLogger(ManufacturerServiceImpl.class);
    private ManufacturerRepository manufacturerRepository;


    @Autowired
    public void setRepository(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository=manufacturerRepository;
    }

    @Override
    public List<Manufacturer> getAll() {
        LOGGER.debug("Get all  Manufacturers");
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getById(UUID id) {
        LOGGER.debug("Get  Manufacturers by ID");
        return manufacturerRepository.findById(id)
                .orElseThrow(()->new ManufacturerNotExistException(String.format("Manufacturers with ID = %s not found" + id)));
    }

    @Override
    public void save(Manufacturer entity) {
        LOGGER.debug("Save Manufacturers");
        if (manufacturerRepository.findByName(entity.getName()).isPresent()){
            throw new ManufacturerAlreadyExistException("Manufacturer already exist: " +  entity.getName());
        }
        manufacturerRepository.save(entity);

    }

    @Override
    public void delete(UUID id) {
        LOGGER.debug("Deleting Manufacturers");
        manufacturerRepository.deleteById(id);
    }

    @Override
    public Manufacturer update(Manufacturer entity) {
        LOGGER.debug("Updating Manufacturers");
        return manufacturerRepository.save(entity);
    }

    @Override
    public Manufacturer getByName(String name) {
        LOGGER.debug("Get Manufacturers by name");

        return manufacturerRepository.findByName(name)
                .orElseThrow(()->new ManufacturerNotExistException("Manufacturer not found"));
    }



}
