package net.springapp.validator;

import net.springapp.model.User;
import net.springapp.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class UserValidator implements Validator {
    private final String RESOURCE_NAME = "validation";
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, new Locale("en"));

    @Autowired
    UserService userService;

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty", resourceBundle.getString(ValidationTextConst.NOT_EMPTY));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty", resourceBundle.getString(ValidationTextConst.NOT_EMPTY));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty", resourceBundle.getString(ValidationTextConst.NOT_EMPTY));

        EmailValidator emailValidator = EmailValidator.getInstance();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty");

    }


    public void isEmailDuplicate(User user, Errors errors) {


    }
}
