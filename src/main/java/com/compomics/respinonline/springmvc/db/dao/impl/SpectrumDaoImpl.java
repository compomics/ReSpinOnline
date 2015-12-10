package com.compomics.respinonline.springmvc.db.dao.impl;

import com.compomics.respinonline.springmvc.db.dao.AbstractDao;
import com.compomics.respinonline.springmvc.db.dao.SpectrumDao;
import com.compomics.respinonline.springmvc.model.Spectrum;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("spectrumDao")
public class SpectrumDaoImpl extends AbstractDao<Long, Spectrum> implements SpectrumDao {

    @Override
    public Spectrum findById(long id) {
        return getByKey(id);
    }

    @Override
    public void saveSpectrum(Spectrum spectrum) {
        persist(spectrum);
    }

    @Override
    public Spectrum findSpectrumByAssayAndSpectrumID(String spectrum_id, String experiment) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("spectrum_id", spectrum_id));
        criteria.add(Restrictions.eq("assay", experiment));
        return (Spectrum) criteria.uniqueResult();
    }
}
