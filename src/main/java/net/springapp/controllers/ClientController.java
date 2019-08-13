package net.springapp.controllers;

import net.springapp.model.barbershop.Client;
import net.springapp.service.ClientService;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/clientMenu")
    public String login() {
        return "clientMenu";
    }

    @GetMapping("/personal")
    public String personalCabinet(Model model, Principal principal) throws Exception {

        String userAccountName = principal.getName();

        Optional<Client> client = clientService.findClient(userAccountName);

        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "personal";
        } else {
            throw new Exception("From personalCabinet");
        }
    }

    @PostMapping("/addMoney")
    public String addToClientBalance(Principal principal, @ModelAttribute("money") int money)
    {
        String userAccountName = principal.getName();
        clientService.addMoneyToBalance(money, userAccountName);

        return "redirect:personal";

    }


}
