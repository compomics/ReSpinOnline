package com.compomics.respinonline.springmvc.controller;

import com.compomics.respinonline.springmvc.db.service.IdentificationService;
import com.compomics.respinonline.springmvc.db.service.SpectrumService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compomics.respinonline.springmvc.model.Identification;
import com.compomics.respinonline.springmvc.model.Spectrum;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    IdentificationService idService;

    @Autowired
    SpectrumService spService;

    @Autowired
    MessageSource messageSource;


    /*
     * This method will list all existing employees.
     */
    @RequestMapping(value = {"/", "/respin"}, method = RequestMethod.GET)
    public String listIdentifications(ModelMap model) {
        List<Identification> identifications = idService.findAllIdentifications();
        model.addAttribute("identifications", identifications);
        List<Identification> identificationsfilteredPepAndAssay = idService.findAllIdentificationsByExperimentAndPeptide("1", "KENNETH");
        model.addAttribute("identificationsfilteredpep", identificationsfilteredPepAndAssay);
        return "allidents";
    }


    /*
     * This method will be called on form submission, handling POST request for
     * updating employee in database. It also validates the user input
     */
    @RequestMapping(value = {"respin/view/{id}"}, method = RequestMethod.GET)
    public String viewIdentification(ModelMap model, @PathVariable int id) {
        Identification selectedIdentification = idService.findById(id);
        Spectrum selectedSpectrum = spService.findBySpectrumIdAndExperiment(selectedIdentification.getSpectrumID(), selectedIdentification.getAssay());
        model.addAttribute("identification", selectedIdentification);
        model.addAttribute("spectrum", selectedSpectrum);
        return "spectrumviewer";
    }

}
