package com.compomics.respinonline.springmvc.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compomics.respinonline.springmvc.db.dao.SpectrumDao;
import com.compomics.respinonline.springmvc.db.service.SpectrumService;
import com.compomics.respinonline.springmvc.model.Spectrum;

@Service("spectrumService")
@Transactional
public class SpectrumServiceImpl implements SpectrumService {

    @Autowired
    private SpectrumDao spectrumDao;

    @Override
    public Spectrum findById(int id) {
        return spectrumDao.findById(id);
    }

    @Override
    public Spectrum findBySpectrumIdAndExperiment(String spectrum_id, String experiment) {
        return spectrumDao.findSpectrumByAssayAndSpectrumID(spectrum_id, experiment);
    }

    @Override
    public void updateSpectrum(Spectrum spectrum) {
        Spectrum entity = spectrumDao.findById(spectrum.getId());
        if (entity != null) {
            entity.setAssay(spectrum.getAssay());
            entity.setCharge(spectrum.getCharge());
            entity.setFile(spectrum.getFile());
            entity.setMs2Peaks(spectrum.getMs2Peaks());
            entity.setPrecursor_mz(spectrum.getPrecursor_mz());
            entity.setPrecursor_intensity(spectrum.getPrecursor_intensity());
            entity.setSpectrum_id(spectrum.getSpectrum_id());
        }
    }

    @Override
    public void saveSpectrum(Spectrum spectrum) {
        spectrumDao.saveSpectrum(spectrum);
    }

}
