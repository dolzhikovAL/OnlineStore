package com.DA.store.controller;


import com.DA.store.controller.exeption.ErrorMessage;
import com.DA.store.controller.exeption.ManufacturerAlreadyExistError;
import com.DA.store.controller.exeption.ManufacturerNotExistException;
import com.DA.store.model.Manufacturer;
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
@RequestMapping(path = "/manufacturer")
public class ManufacturerController {

    private ManufacturerService manufacturerService;
    private ProductService productService;

    @Autowired
    public void setManufacturers(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;

    }

    @GetMapping(path = "/showManufacturers")
    public String showManufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerService.getAll());
        return "show_manufacturers";
    }

    @GetMapping(path = "/get")
    public ModelAndView getManufacturer(@RequestParam(name = "id") java.util.UUID id, ModelAndView model) {
        final Manufacturer manufacturer = manufacturerService.getById(id);
        model.setViewName("manufacturer_details");
        model.addObject("manufacturer", manufacturer);
        return model;
    }

    @GetMapping(path = "/createManufacturer")
    public String getCreateManufacturerView(Model model) {
        return "create_manufacturer";
    }

    @PostMapping(name = "createManufacturer")
    public String createManufacturer(@ModelAttribute("manufacturer") @Valid Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create_manufacturer";
        }
        try {
            manufacturerService.save(manufacturer);
            model.addAttribute("name", manufacturer.getName());
            return "manufacturer_created";
        } catch (ManufacturerAlreadyExistError e) {
            model.addAttribute("errors", List.of(new ErrorMessage("", e.getMessage())));
            return "create_manufacturer";
        }
    }

    @GetMapping(path = "/find")
    public String findManufacturer(@RequestParam("name") String name, Model model) {
        Manufacturer manufacturer = null;
        try {
            manufacturer = manufacturerService.getByName(name);
        } catch (ManufacturerNotExistException e) {
            return "find_manufacturer";
        }
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer_details";

    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable UUID id, Model model) {
        Manufacturer manufacturer = manufacturerService.getById(id);
        model.addAttribute("manufacturer", manufacturer);
        return "edit_manufacturer";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public ModelAndView editsave(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        manufacturerService.update(manufacturer);
        return new ModelAndView("redirect:/manufacturer/showManufacturers");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable java.util.UUID id) {
        manufacturerService.delete(id);
        return new ModelAndView("redirect:/manufacturer/showManufacturers");
    }

    @ModelAttribute("manufacturer")
    public Manufacturer getDefaultCourse() {
        return new Manufacturer();
    }


}
