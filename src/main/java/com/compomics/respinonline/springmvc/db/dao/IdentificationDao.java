package com.compomics.respinonline.springmvc.db.dao;

import com.compomics.respinonline.springmvc.model.Identification;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;

public interface IdentificationDao {

    Identification findById(int id);

    void saveIdentification(Identification reSpinIdentification);

    public List<Identification> findAllIdentifications();

    public List<Identification> findAllIdentificationsByExperiment(String experiment);

    public List<Identification> findAllIdentificationsByPeptide(String sequence);

    public List<Identification> findAllIdentificationsByAssayAndPeptide(String experiment, String sequence);

    public List<Identification> findAllFilteredIdentifications(HashMap<String, Object> requestedCriteria);

}
