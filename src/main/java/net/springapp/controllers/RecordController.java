package net.springapp.controllers;

import net.springapp.model.barbershop.Record;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

@Controller
public class RecordController {

    @Autowired
    RecordService recordService;

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
    public String visitRegistration(Model model)
    {
        List<Record> recordList = recordService.getAllRecords();
        model.addAttribute("recordList", recordList);

        return "listRecords";
    }

}
