package net.springapp.controllers;

import net.springapp.model.User;
import net.springapp.service.SecurityService;
import net.springapp.service.UserService;
import net.springapp.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    SecurityService securityService;

    @GetMapping({"/registration","/new-user"})
    public String showRegistrationForm(Model model) {
        return AddDataToRegistForm(new User(), model,false);
    }

    @PostMapping({"/registration","/new-user"})
    public String getRegistration(@ModelAttribute("userForm") User userForm, BindingResult result, Model model){
        return getUserInfo(userForm, result, model, false);
    }

    @GetMapping("/list_users")
    public String showListUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "listUsers";
    }

    @GetMapping("/edit-user-{id}")
    public String editUser(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        return AddDataToRegistForm(user, model,true);
    }

    @PostMapping("/edit-user-{id}")
    public String saveEditUser(@ModelAttribute("userForm") User userForm, BindingResult result, Model model) {
        return getUserInfo(userForm, result, model, true);
    }

    @GetMapping("/delete-user-{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        return "redirect:/list_users";
    }

    //Utility methods
    private String AddDataToRegistForm(User user, Model model,boolean edit) {
        model.addAttribute("userForm", user);
        model.addAttribute("listroles", userService.findAllRole());
        model.addAttribute("edit", edit);
        return "registration";
    }

    private String getUserInfo(User userForm, BindingResult result, Model model,boolean edit) {
        userValidator.validate(userForm, result);
        if (!edit) userValidator.isEmailDuplicate(userForm,result);
        try {
            userService.save(userForm);
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("email", "Duplicate.userForm.email");
            logger.info("Try to enter duplicate email [] to database", userForm.getUserName());
            return AddDataToRegistForm(userForm, model,false);
        }

        securityService.autologin(userForm.getUserName(), userForm.getPassword());
        model.addAttribute("users", userForm);
        if (edit) return "redirect:/list_users";

        return "index";
    }
}
