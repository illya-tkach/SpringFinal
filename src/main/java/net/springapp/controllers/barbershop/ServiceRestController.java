package net.springapp.controllers.barbershop;

import net.springapp.model.barbershop.Service;
import net.springapp.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceRestController {
    @Autowired
    ServiceService service;

    @RequestMapping("/servicesAll")
    public List<Service> allServices()  {
        return service.getAllServices();
    }
}
