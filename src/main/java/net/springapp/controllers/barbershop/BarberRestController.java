package net.springapp.controllers.barbershop;

import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.Record;
import net.springapp.model.barbershop.Service;
import net.springapp.model.barbershop.ServiceStatus;
import net.springapp.service.BarberService;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
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
//        Record record = new Record();
//        record.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record.setLocalTime(LocalTime.of(10,00));
//        record.setStatus(ServiceStatus.SERVED);
//        recordService.save(record);
//
//        Record record2 = new Record();
//        record2.setLocalDate(LocalDate.of(2019, Month.JULY, 18));
//        record2.setLocalTime(LocalTime.of(12,00));
//        record2.setStatus(ServiceStatus.SERVED);
//        recordService.save(record2);
//        Record record3 = new Record();
//        record3.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record3.setLocalTime(LocalTime.of(9,30));
//        record3.setStatus(ServiceStatus.SERVED);
//        recordService.save(record3);
//////
//        Record record4 = new Record();
//        record4.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record4.setLocalTime(LocalTime.of(10,30));
//        record4.setStatus(ServiceStatus.SERVED);
//        recordService.save(record4);
//
//        Record record5 = new Record();
//        record5.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record5.setLocalTime(LocalTime.of(11,30));
//        record5.setStatus(ServiceStatus.SERVED);
//        recordService.save(record5);
//
//        Record record6 = new Record();
//        record6.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record6.setLocalTime(LocalTime.of(12,30));
//        record6.setStatus(ServiceStatus.SERVED);
//        recordService.save(record6);
//
//        Record record7 = new Record();
//        record7.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record7.setLocalTime(LocalTime.of(13,30));
//        record7.setStatus(ServiceStatus.SERVED);
//        recordService.save(record7);
//
//        Record record8 = new Record();
//        record8.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record8.setLocalTime(LocalTime.of(15,30));
//        record8.setStatus(ServiceStatus.SERVED);
//        recordService.save(record8);
//
//        Record record9 = new Record();
//        record9.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record9.setLocalTime(LocalTime.of(16,30));
//        record9.setStatus(ServiceStatus.SERVED);
//        recordService.save(record9);
//
//        Record record10 = new Record();
//        record10.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record10.setLocalTime(LocalTime.of(17,30));
//        record10.setStatus(ServiceStatus.SERVED);
//        recordService.save(record10);
//
//        Record record11 = new Record();
//        record11.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record11.setLocalTime(LocalTime.of(18,30));
//        record11.setStatus(ServiceStatus.SERVED);
//        recordService.save(record11);
//
//        Record record12 = new Record();
//        record12.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record12.setLocalTime(LocalTime.of(19,30));
//        record12.setStatus(ServiceStatus.SERVED);
//        recordService.save(record12);
//
//        Record record13 = new Record();
//        record13.setLocalDate(LocalDate.of(2019, Month.JULY, 17));
//        record13.setLocalTime(LocalTime.of(20,30));
//        record13.setStatus(ServiceStatus.SERVED);
//        recordService.save(record13);
      //  List<Record> records = recordService.getAllRecordsByDate(LocalDate.of(2019, Month.JULY, 17));
//
//        System.out.println(records);
        return barberService.getAllBarbers();
    }
}
