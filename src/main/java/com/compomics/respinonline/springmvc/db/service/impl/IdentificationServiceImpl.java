package com.compomics.respinonline.springmvc.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compomics.respinonline.springmvc.db.dao.IdentificationDao;
import com.compomics.respinonline.springmvc.db.service.IdentificationService;
import com.compomics.respinonline.springmvc.model.Identification;

@Service("identificationService")
@Transactional
public class IdentificationServiceImpl implements IdentificationService {

    @Autowired
    private IdentificationDao dao;

    @Override
    public Identification findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void updateIdentification(Identification reSpinIdentification) {
        Identification entity = dao.findById(reSpinIdentification.getId());
        if (entity != null) {
            entity.setAssay(reSpinIdentification.getAssay());
            entity.setConfidence(reSpinIdentification.getConfidence());
            entity.setSequence(reSpinIdentification.getSequence());
            entity.setSpectrumID(reSpinIdentification.getSpectrumID());
        }
    }

    @Override
    public List<Identification> findAllIdentifications() {
        return dao.findAllIdentifications();
    }

    @Override
    public List<Identification> findAllIdentificationsByExperiment(String experiment) {
        return dao.findAllIdentificationsByExperiment(experiment);
    }

    @Override
    public List<Identification> findAllIdentificationsByPeptide(String sequence) {
        return dao.findAllIdentificationsByPeptide(sequence);
    }

    @Override
    public void saveIdentification(Identification reSpinIdentification) {
        dao.saveIdentification(reSpinIdentification);
    }

    @Override
    public List<Identification> findAllIdentificationsByExperimentAndPeptide(String experiment, String sequence) {
        return dao.findAllIdentificationsByAssayAndPeptide(experiment, sequence);
    }

}
