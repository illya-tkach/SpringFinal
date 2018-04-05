package net.springapp.controllers;

import net.springapp.model.Manufacturer;
import net.springapp.repository.ManufacturerService;
import net.springapp.validator.ManufacturerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManufacturersContreller {

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    ManufacturerValidation validation;

    @GetMapping("/list_manufacturer")
    private String showManufacturer(Model model){
        List<Manufacturer> list = manufacturerService.getAllManufacturer();
        model.addAttribute("listmanuf", list);
        model.addAttribute("loggedinuser", DefaultController.getPrincipal());
        return "listManufacturer";
    }

    @GetMapping("/new-manufacturer")
    private String newManufacturer(Model model){
        return addDataToManufactForm(model, new Manufacturer());
    }

    @PostMapping("/new-manufacturer")
    private String saveManufacturer(@ModelAttribute("manufacturerForm") Manufacturer manufacturer, BindingResult result, Model model){
        return getManufactInfo(manufacturer, result, model,false);
    }

    @GetMapping("/edit-manufacturer-{id}")
    private String editManufacturer(@PathVariable String id, Model model){
        return addDataToManufactForm(model, manufacturerService.findById(id));
    }

    @PostMapping("/edit-manufacturer-{id}")
    private String saveEditManufacturer(@ModelAttribute("manufacturerForm") Manufacturer manufacturer,
                                        BindingResult result, Model model){

        return getManufactInfo(manufacturer, result, model,true);

    }

    @GetMapping("/delete-manufacturer-{id}")
    public String deleteUser(@PathVariable String id) {
        manufacturerService.deleteById(id);
        return "redirect:list_manufacturer";
    }


    private String getManufactInfo(@ModelAttribute("manufacturerForm") Manufacturer manufacturer,
                                   BindingResult result, Model model, boolean edit) {

        validation.validate(manufacturer,result);
        if (!edit) validation.isNameDuplicate(manufacturer,result);

        if (result.hasErrors()) {
            model.addAttribute("manufacturerForm",manufacturer);
            model.addAttribute("edit",false);
            model.addAttribute("loggedinuser", DefaultController.getPrincipal());
            return "manufacturer";
        }

        manufacturerService.save(manufacturer);
        return showManufacturer(model);
    }

    private String addDataToManufactForm(Model model, Manufacturer manufacturer) {
        model.addAttribute("manufacturerForm", manufacturer);
        model.addAttribute("loggedinuser", DefaultController.getPrincipal());
        return "manufacturer";
    }





}
