package net.springapp.service.impl;

import net.springapp.model.barbershop.Service;
import net.springapp.repository.ServiceRepository;
import net.springapp.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
}
