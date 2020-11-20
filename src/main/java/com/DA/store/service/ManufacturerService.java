package com.DA.store.service;

import com.DA.store.model.BaseEntity;
import com.DA.store.model.Manufacturer;

public interface ManufacturerService extends BaseService<Manufacturer> {
    Manufacturer getByName(String name);
}
