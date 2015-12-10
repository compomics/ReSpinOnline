package com.compomics.respinonline.springmvc.db.dao.impl;

import com.compomics.respinonline.springmvc.db.dao.AbstractDao;
import com.compomics.respinonline.springmvc.db.dao.SpectrumMetadataDao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.compomics.respinonline.springmvc.model.SpectrumMetadata;

@Repository("spectrumMetadataDao")
public class SpectrumMetadataDaoImpl extends AbstractDao<Integer, SpectrumMetadata> implements SpectrumMetadataDao {

    @Override
    public SpectrumMetadata findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveIdentification(SpectrumMetadata reSpinIdentification) {
        persist(reSpinIdentification);
    }

    @Override
    public List<SpectrumMetadata> findAllSpectrumMetadataByExperiment(String experiment) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("assay", experiment));
        return (List<SpectrumMetadata>) criteria.list();
    }

    @Override
    public List<SpectrumMetadata> findAllSpectrumMetadataBySpectrumID(String spectrum_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("spectrum_id", spectrum_id));
        return (List<SpectrumMetadata>) criteria.list();
    }

    @Override
    public SpectrumMetadata findSpectrumByAssayAndSpectrumID(String experiment, String spectrum_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("spectrum_id", spectrum_id));
        criteria.add(Restrictions.eq("assay", experiment));
        return (SpectrumMetadata) criteria.uniqueResult();
    }

    @Override
    public List<SpectrumMetadata> findAllSpectrumMetadata() {
        Criteria criteria = createEntityCriteria();
        return (List<SpectrumMetadata>) criteria.list();
    }
  
    
}
