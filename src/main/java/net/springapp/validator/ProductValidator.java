package net.springapp.validator;

import net.springapp.model.Product;
import net.springapp.service.ProductService;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProductValidator implements Validator {
    private static final Pattern patternForName = Pattern.compile("^\\s*[\\da-zA-Z][\\da-zA-Z\\s][\\d-a-z-A-Z\\s]*$");

    @Autowired
    ProductService productService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Product product = (Product) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

        Matcher matcher = patternForName.matcher(product.getName());
        if(!matcher.matches()) {
            errors.rejectValue("name", "name.product.isnt.correct");
        }

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");

//        if (product.getPrice() instanceof  BigDecimal){
//            if (!isBetween(product.getPrice(),BigDecimal.valueOf(0),BigDecimal.valueOf(1000000))){
//               errors.rejectValue("price","typeMismatch.price");
//            }
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manufacturer", "manufacturer.isnt.select");

    }

    public void isNameDuplicate(Product product, Errors errors) {
        //проверить что не только название одинаковое но и прогизводитель

        if (productService.findByName(product.getName()) != null) {
            errors.rejectValue("name", "Duplicate.product.name");
        }
    }

    boolean isBetween(BigDecimal price, BigDecimal start, BigDecimal end){
        return price.compareTo(start) > 0 && price.compareTo(end) < 0;
    }

}
