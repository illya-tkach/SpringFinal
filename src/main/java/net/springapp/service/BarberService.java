package net.springapp.service;

import net.springapp.model.barbershop.Barber;

import java.util.List;

public interface BarberService {
    List<Barber> getAllBarbers();
    List<Barber> barbersByService(long serviceId);
    List<Barber> barbersByDateAndTime (String date);
}
