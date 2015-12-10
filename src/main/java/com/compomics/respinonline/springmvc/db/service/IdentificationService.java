package com.compomics.respinonline.springmvc.db.service;

import java.util.List;

import com.compomics.respinonline.springmvc.model.Identification;

public interface IdentificationService {

    Identification findById(int id);

    void saveIdentification(Identification employee);

    void updateIdentification(Identification employee);

    List<Identification> findAllIdentifications();

    List<Identification> findAllIdentificationsByExperiment(String experiment);

    List<Identification> findAllIdentificationsByPeptide(String sequence);

    List<Identification> findAllIdentificationsByExperimentAndPeptide(String experiment, String sequence);
}
