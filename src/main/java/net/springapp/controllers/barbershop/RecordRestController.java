package net.springapp.controllers.barbershop;

import net.springapp.model.barbershop.Record;
import net.springapp.model.barbershop.ServiceStatus;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecordRestController {
    @Autowired
    RecordService recordService;

    @RequestMapping("/dateAll")
    public List<LocalDate> allAvailableDatesForBooking()  {
        return recordService.findDateByStatus(ServiceStatus.NOT_RESERVED);
    }

    @PostMapping("/timeAll-{date}")
    public List<LocalTime> allAvailableTimeForBookingByDate(@PathVariable("date") String date)  {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(date, formatter);

        return recordService.getAllRecordsTimeByDate(localDate);
    }


}
