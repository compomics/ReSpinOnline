package com.compomics.respinonline.springmvc.db.service;

import com.compomics.respinonline.springmvc.model.Spectrum;

public interface SpectrumService {

    Spectrum findById(int id);

    Spectrum findBySpectrumIdAndExperiment(String spectrum_id, String experiment);

    void updateSpectrum(Spectrum spectrum);

    void saveSpectrum(Spectrum spectrum);
}
