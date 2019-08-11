package net.springapp.service.impl;

import net.springapp.model.barbershop.Barber;
import net.springapp.repository.BarberRepository;
import net.springapp.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BarberServiceImpl implements BarberService {

    @Autowired
    BarberRepository barberRepository;

    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }
}
