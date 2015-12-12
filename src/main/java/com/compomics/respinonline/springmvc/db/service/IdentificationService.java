package com.compomics.respinonline.springmvc.db.service;

import java.util.List;

import com.compomics.respinonline.springmvc.model.Identification;
import java.util.HashMap;
import org.hibernate.Criteria;

public interface IdentificationService {

    Identification findById(int id);

    void saveIdentification(Identification ident);

    void updateIdentification(Identification ident);

    List<Identification> findAllIdentifications();

   public List<Identification> findAllFilteredIdentifications(HashMap<String, Object> requestedCriteria);

    List<Identification> findAllIdentificationsByExperiment(String experiment);

    List<Identification> findAllIdentificationsByPeptide(String sequence);

    List<Identification> findAllIdentificationsByExperimentAndPeptide(String experiment, String sequence);
    
}
