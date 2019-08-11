package net.springapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @PostMapping("/booking")
    public String visitRegistration(Model model, @ModelAttribute("barberName") String barberName,
                                          @ModelAttribute("serviceName") String serviceName,
                                          @ModelAttribute("dateAndTime") String dateAndTime)
    {
        model.addAttribute("barberName", barberName);

        return "booking";
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("loggedinuser", DefaultController.getPrincipal());
        return "index";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    public static String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
