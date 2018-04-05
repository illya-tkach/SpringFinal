package net.springapp.controllers;

import net.springapp.model.Manufacturer;
import net.springapp.model.Product;
import net.springapp.repository.ManufacturerService;
import net.springapp.service.ProductService;
import net.springapp.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductValidator validation;

    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/list_products")
    private String showManufacturer(Model model){
        List<Product> list = productService.getAllProducts();
        model.addAttribute("listproducts", list);
        model.addAttribute("loggedinuser", DefaultController.getPrincipal());
        return "listProducts";
    }

    @GetMapping("/new-product")
    private String newProduct(Model model){
        return addDataToProductForm(model, new Product());
    }

    @PostMapping("/new-product")
    private String saveManufacturer(@ModelAttribute("productForm") @Valid Product product, BindingResult result, Model model){
        return getProductInfo(product, result, model,false);
    }

    @GetMapping("/edit-product-{id}")
    private String editProduct(@PathVariable String id, Model model){
        return addDataToProductForm(model, productService.findById(id));
    }

    @PostMapping("/edit-product-{id}")
    private String saveEditProduct(@ModelAttribute("productForm") Product product,
                                        BindingResult result, Model model){

        return getProductInfo(product, result, model,true);

    }

    @GetMapping("/delete-product-{id}")
    public String deleteUser(@PathVariable String id) {
        productService.deleteById(id);
        return "redirect:list_products";
    }

    private String getProductInfo(Product product, BindingResult result, Model model, boolean edit) {

            validation.validate(product,result);
            if (!edit) validation.isNameDuplicate(product,result);

            if (result.hasErrors()) return addDataToProductForm(model,product);

            productService.save(product);
            return showManufacturer(model);
    }

    private String addDataToProductForm(Model model, Product product) {
        model.addAttribute("productForm", product);
        List<Manufacturer> list = manufacturerService.getAllManufacturer();
        model.addAttribute("listmanufacturers",list);
        model.addAttribute("loggedinuser", DefaultController.getPrincipal());
        return "product";
    }
}
