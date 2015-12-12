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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
        model.addAttribute("expanded", 0);
        return "identifications";
    }

    /*
     * This method will list all identifications
     */
    @RequestMapping(value = {"/query",}, method = RequestMethod.GET)
    public String filterIdentifications(ModelMap model,
            @RequestParam(value = "sequence", required = false) String sequence,
            @RequestParam(value = "confidence", required = false) Double confidence,
            @RequestParam(value = "experiment", required = false) String experiment,
            @RequestParam(value = "expanded", required = true) Integer expanded) {
        System.out.println("Just verifying that I recieved " + expanded);
        model.addAttribute("expanded", expanded);
        HashMap<String, Object> requestedCriteria = new HashMap<>();
        if (sequence != null && !sequence.isEmpty()) {
            requestedCriteria.put("sequence", sequence);
        }
        if (confidence != null) {
            requestedCriteria.put("confidence", new BigDecimal(confidence));
            model.addAttribute("confidence", confidence);
        }
        if (experiment != null && !experiment.isEmpty()) {
            requestedCriteria.put("assay", experiment);
            model.addAttribute("experiment", experiment);
        }
        List<Identification> identifications = new ArrayList<>();
        if (!requestedCriteria.isEmpty()) {
            identifications.addAll(idService.findAllFilteredIdentifications(requestedCriteria));
        }
        model.addAttribute("identifications", identifications);
        return "identifications";
    }


    /*
     * This method will be called on form submission, handling POST request for
     * updating identification in database. It also validates the user input
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
