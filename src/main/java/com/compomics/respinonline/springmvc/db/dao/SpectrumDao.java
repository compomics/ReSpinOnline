package com.compomics.respinonline.springmvc.db.dao;

import com.compomics.respinonline.springmvc.model.Spectrum;

public interface SpectrumDao {

    Spectrum findById(long id);

    void saveSpectrum(Spectrum spectrum);

    public Spectrum findSpectrumByAssayAndSpectrumID(String spectrum_id, String experiment);

}
