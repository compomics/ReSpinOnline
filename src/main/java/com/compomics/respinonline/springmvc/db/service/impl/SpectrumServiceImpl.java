package com.compomics.respinonline.springmvc.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compomics.respinonline.springmvc.db.dao.SpectrumDao;
import com.compomics.respinonline.springmvc.db.dao.SpectrumMetadataDao;
import com.compomics.respinonline.springmvc.db.service.SpectrumService;
import com.compomics.respinonline.springmvc.model.Spectrum;
import com.compomics.respinonline.springmvc.model.SpectrumMetadata;

@Service("spectrumService")
@Transactional
public class SpectrumServiceImpl implements SpectrumService {
    
    @Autowired
    private SpectrumDao spectrumDao;
    
    @Autowired
    private SpectrumMetadataDao spectrumMetadataDao;
    
    @Override
    public Spectrum findById(int id) {
        return spectrumDao.findById(id);
    }
    
    @Override
    public Spectrum findBySpectrumIdAndExperiment(String spectrum_id, String experiment) {
        SpectrumMetadata metadata= spectrumMetadataDao.findSpectrumByAssayAndSpectrumID(spectrum_id, experiment);
        return spectrumDao.findById(metadata.getId());
    }
    
    @Override
    public void updateSpectrum(Spectrum spectrum) {
        Spectrum entity = spectrumDao.findById(spectrum.getId());
        if (entity != null) {
            spectrum.setMetadata(spectrum.getMetadata());
            spectrum.setSpectrum(spectrum.getSpectrum());
        }
    }
    
    @Override
    public void saveSpectrum(Spectrum spectrum) {
        spectrumDao.saveIdentification(spectrum);
    }
    
}
