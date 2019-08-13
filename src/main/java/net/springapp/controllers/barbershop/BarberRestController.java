package net.springapp.controllers.barbershop;

import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.Record;
import net.springapp.model.barbershop.Service;
import net.springapp.model.barbershop.ServiceStatus;
import net.springapp.service.BarberService;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@RestController
public class BarberRestController {
    @Autowired
    BarberService barberService;

    @Autowired
    RecordService recordService;


    @RequestMapping("/barbersAll")
    public List<Barber> allBarbers()  {
        return barberService.getAllBarbers();
    }

    @RequestMapping("/barbersByService-{id}")
    public List<Barber> barbersByService(@PathVariable String id)  {
        return barberService.barbersByService(Long.parseLong(id));
    }

    @RequestMapping("/barbersByCalendar-{date}")
    public List<Barber> barbersByCalendar(@PathVariable String date)  {

        return barberService.barbersByDateAndTime(date);

    }
}
