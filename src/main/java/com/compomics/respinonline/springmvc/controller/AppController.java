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
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;

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
     * This method will forward to the home page
     */
    @RequestMapping(value = {"/",}, method = RequestMethod.GET)
    public String listIdentifications(ModelMap model) {
        return "identifications";
    }

    /*
     * This method will list all identifications
     */
    @RequestMapping(value = {"/query",}, method = RequestMethod.GET)
    public String filterIdentifications(ModelMap model, 
            @RequestParam(value = "query_value", required = false) String queryValue, 
            @RequestParam(value = "query_type", required = false) String queryType) {
        List<Identification> identifications = new ArrayList<>();
        if (queryType.equalsIgnoreCase("sequence")) {
            identifications = idService.findAllIdentificationsByPeptide(queryValue);
        } else if (queryType.equalsIgnoreCase("experiment")) {
            identifications = idService.findAllIdentificationsByExperiment(queryValue);
        }
        model.addAttribute("query_value", queryValue);
        model.addAttribute("query_type", queryType);
        model.addAttribute("identifications", identifications);
        return "identifications";
    }


    /*
     * This method will be called on form submission, handling POST request for
     * updating employee in database. It also validates the user input
     */
    @RequestMapping(value = {"respin/view/{id}"}, method = RequestMethod.GET)
    public String viewIdentification(ModelMap model,
            @PathVariable int id
    ) {
        Identification selectedIdentification = idService.findById(id);
        Spectrum selectedSpectrum = spService.findBySpectrumIdAndExperiment(selectedIdentification.getSpectrumID(), selectedIdentification.getAssay());
        model.addAttribute("identification", selectedIdentification);
        model.addAttribute("spectrum", selectedSpectrum);
        return "spectrumviewer";
    }

}
