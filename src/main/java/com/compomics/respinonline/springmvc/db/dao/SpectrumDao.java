package com.compomics.respinonline.springmvc.db.dao;

import com.compomics.respinonline.springmvc.model.Spectrum;

public interface SpectrumDao {

    Spectrum findById(int id);

    void saveIdentification(Spectrum spectrum);

}
