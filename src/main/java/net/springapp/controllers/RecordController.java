package net.springapp.controllers;

import lombok.extern.slf4j.Slf4j;
import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.Record;
import net.springapp.model.barbershop.ServiceStatus;
import net.springapp.service.BarberService;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    BarberService barberService;

    @PostMapping("/booking")
    public String visitRegistration(Model model, @ModelAttribute("barberId") long barberId,
                                    @ModelAttribute("serviceId") long serviceId)
    {
        int cost = recordService.calculateCost(barberId, serviceId);
        model.addAttribute("cost", cost);

        return "paymentView";
    }

    @PostMapping("/payment")
    public String payAndSaveRecord(Principal principal, Model model, @ModelAttribute("barberId") long barberId,
                                   @ModelAttribute("serviceId") long serviceId, @ModelAttribute("dateAndTime") String dateAndTime)
    {
        try {
            recordService.payAndSaveRecord(barberId, serviceId, dateAndTime, principal.getName());
            //todo add exception handling
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("bookingSuccess", "Not Enough Money. Please Add some money.");
            return "homeView";
        }

        model.addAttribute("bookingSuccess", "You are successfully added your record to the system.");
        return "homeView";
    }

    @GetMapping("/recordList")
    public String visitRegistration(Model model) {
        List<Record> recordList = recordService.getAllRecords();
        model.addAttribute("recordList", recordList);

        return "listRecords";
    }

    @GetMapping("/newRecord")
    public String newRecordMenu(Model model){
        List<Barber> barbers = barberService.getAllBarbers();
        model.addAttribute("barbers", barbers);

        return "newRecordPage";
    }

    @PostMapping("/newRecord")
    public String addNewRecord(@ModelAttribute("date") String date,
                                     @ModelAttribute("time") String time,
                                            @ModelAttribute("barberId") String barberId ){
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(date, dateformatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        Barber selectedBarber = barberService.findById(Long.parseLong(barberId));
        Record newRecord = Record.builder().barber(selectedBarber).localDate(localDate).localTime(localTime).status(ServiceStatus.NOT_RESERVED).build();

        recordService.save(newRecord);

        return "redirect:recordList";
    }


    @GetMapping("/served-{id}")
    public String setServedStatus(@PathVariable("id") long recordId)  {

        Record record = recordService.getRecordById(recordId);

        record.setStatus(ServiceStatus.SERVED);

        recordService.save(record);

        log.info("Record with id {} served.", recordId);

        return "redirect:recordList";
    }

    @GetMapping("/remove-{id}")
    public String removeRecord(@PathVariable("id") long recordId)  {

        recordService.remove(recordId);

        log.info("Record with id {} deleted.", recordId);
        return "redirect:recordList";
    }
}
