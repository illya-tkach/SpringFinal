package net.springapp.service;

import net.springapp.model.Manufacturer;
import net.springapp.repository.ManufacturerRepository;
import net.springapp.repository.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public void save(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(String id) {
        return manufacturerRepository.findOne(Long.parseLong(id));
    }

    @Override
    public void deleteById(String id) {
        manufacturerRepository.delete(Long.parseLong(id));
    }

    @Override
    public Manufacturer findByName(String name) {
        return manufacturerRepository.findByName(name);
    }
}
