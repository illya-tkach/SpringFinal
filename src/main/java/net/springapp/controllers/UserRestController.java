package net.springapp.controllers;

import net.springapp.model.User;
import net.springapp.service.SecurityService;
import net.springapp.service.UserService;
import net.springapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    SecurityService securityService;
//
//    @InitBinder("userFromForm")
//    public void setupBinder(WebDataBinder binder) {
//        binder.addValidators(userValidator);
//    }

    @PostMapping(value = "/registrationJson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> greeting(@RequestBody User userFromForm, BindingResult result) throws NoSuchMethodException, MethodArgumentNotValidException {
        userValidator.validate(userFromForm, result);
        if (result.hasErrors()){
            throw new MethodArgumentNotValidException(new MethodParameter(User.class.getConstructor(), 0),result);
        }
        try {
            userService.save(userFromForm);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(userFromForm, HttpStatus.BAD_REQUEST);
        }

        securityService.autologin(userFromForm.getEmail(), userFromForm.getConfirmPassword());

        return new ResponseEntity<>(userFromForm, HttpStatus.CREATED);
    }

}
