package net.springapp.repository;

import net.springapp.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    void save(Manufacturer manufacturer);

    List<Manufacturer> getAllManufacturer();

    Manufacturer findById(String id);

    void deleteById(String id);

    Manufacturer findByName(String name);
}
