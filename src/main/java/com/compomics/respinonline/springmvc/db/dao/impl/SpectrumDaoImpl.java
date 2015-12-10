package com.compomics.respinonline.springmvc.db.dao.impl;

import com.compomics.respinonline.springmvc.db.dao.AbstractDao;
import com.compomics.respinonline.springmvc.db.dao.SpectrumDao;
import com.compomics.respinonline.springmvc.model.Spectrum;
import org.springframework.stereotype.Repository;

@Repository("spectrumDao")
public class SpectrumDaoImpl extends AbstractDao<Integer, Spectrum> implements SpectrumDao {

    @Override
    public Spectrum findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveIdentification(Spectrum spectrum) {
        persist(spectrum);
    }

}
