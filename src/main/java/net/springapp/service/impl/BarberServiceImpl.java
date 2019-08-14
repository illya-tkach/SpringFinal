package net.springapp.service.impl;

import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.ServiceStatus;
import net.springapp.repository.BarberRepository;
import net.springapp.service.BarberService;
import net.springapp.service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BarberServiceImpl implements BarberService {

    @Autowired
    BarberRepository barberRepository;

    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    @Override
    public List<Barber> barbersByService(long serviceId) {
        return barberRepository.findBarbersByService(serviceId);
    }

    @Override
    public List<Barber> barbersByDateAndTime(String dateAndTime) {

        LocalDateTime localDateTime = ServiceUtil.convertToDateAndTime(dateAndTime, "_","MM-dd-yyyy");

        LocalDate localDate = localDateTime.toLocalDate();

        LocalTime localTime = localDateTime.toLocalTime();

        return barberRepository.findBarberByDateAndTime(localDate, localTime, ServiceStatus.NOT_RESERVED);

    }

    @Override
    public Barber findById(long id) {
        return barberRepository.findOne(id);
    }
}
