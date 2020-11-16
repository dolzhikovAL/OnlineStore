package com.DA.store.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
    private static final Logger LOGGER = LogManager.getLogger(ExceptionController.class);

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception ex) {
        LOGGER.error("handleException Exception:", ex);
        final ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}
