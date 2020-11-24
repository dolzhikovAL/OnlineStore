package com.DA.store.controller;

import com.DA.store.controller.exeption.*;
import com.DA.store.model.Manufacturer;
import com.DA.store.model.Product;
import com.DA.store.service.ManufacturerService;
import com.DA.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping(path = "/product")
public class ProductController {
    private ProductService productService;
    private ManufacturerService manufacturerService;

    @Autowired
    public void setProducts(ProductService productService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping(path = "/showProducts")
    public String showManufacturers(Model model) {
        model.addAttribute("products", productService.getAll());
        return "show_products";
    }

    @GetMapping(path = "/get")
    public ModelAndView getProduct(@RequestParam(name = "id") java.util.UUID id, ModelAndView model) {
        final Product product = productService.getById(id);
        model.setViewName("product_details");
        model.addObject("product", product);
        return model;
    }

    @GetMapping(path = "/createProduct")
    public String getCreateProductView(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAll());
        return "create_product";
    }


    @PostMapping(path = "/createProduct")
    public String createProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_product";
        }
        try {
            final Manufacturer manufacturer = manufacturerService.getByName(product.getManufacturer().getName());
            product.setManufacturer(manufacturer);
            productService.save(product);
            model.addAttribute("name", product.getName());
            return "product_created";
        } catch (ProductAlreadyExistException e) {

            model.addAttribute("errors", List.of(new ErrorMessage("", e.getMessage())));
            return "create_product";
        }
    }

    @GetMapping(path = "/findProduct")
    public String showFindProductPage() {
        return "find_product";
    }

    @GetMapping(path = "/find")
    public String findProduct(@RequestParam("name") String name, Model model) {
        try {
            List<Product> products = productService.getByName(name);
            model.addAttribute("products", products);
            return "show_products";
        } catch (ProductNotExistException e) {
            model.addAttribute("error", e.getMessage());
            return "find_product";
        }
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable UUID id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("manufacturers", manufacturerService.getAll());
        return "edit_product";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("product") @Valid Product product) {
        final Manufacturer manufacturer = manufacturerService.getByName(product.getManufacturer().getName());
        product.setManufacturer(manufacturer);
        productService.update(product);
        return new ModelAndView("redirect:/product/showProducts");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable java.util.UUID id) {
        productService.delete(id);
        return new ModelAndView("redirect:/product/showProducts");
    }

    @ModelAttribute("product")
    public Product getDefaultProduct() {
        return new Product();
    }
}