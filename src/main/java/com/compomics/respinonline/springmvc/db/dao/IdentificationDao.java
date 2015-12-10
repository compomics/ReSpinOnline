package com.compomics.respinonline.springmvc.db.dao;

import com.compomics.respinonline.springmvc.model.Identification;
import java.util.List;

public interface IdentificationDao {

    Identification findById(int id);

    void saveIdentification(Identification reSpinIdentification);

    public List<Identification> findAllIdentifications();

    public List<Identification> findAllIdentificationsByExperiment(String experiment);

    public List<Identification> findAllIdentificationsByPeptide(String sequence);

    public List<Identification> findAllIdentificationsByAssayAndPeptide(String experiment, String sequence);
}
