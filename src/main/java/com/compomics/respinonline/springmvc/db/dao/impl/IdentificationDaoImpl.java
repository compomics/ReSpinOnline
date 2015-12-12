package com.compomics.respinonline.springmvc.db.dao.impl;

import com.compomics.respinonline.springmvc.db.dao.AbstractDao;
import com.compomics.respinonline.springmvc.db.dao.IdentificationDao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.compomics.respinonline.springmvc.model.Identification;
import java.util.HashMap;
import java.util.Map.Entry;

@Repository("identificationDao")
public class IdentificationDaoImpl extends AbstractDao<Integer, Identification> implements IdentificationDao {

    @Override
    public Identification findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveIdentification(Identification reSpinIdentification) {
        persist(reSpinIdentification);
    }

    @Override
    public List<Identification> findAllIdentificationsByExperiment(String experiment) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("assay", experiment));
        return (List<Identification>) criteria.list();
    }

    @Override
    public List<Identification> findAllIdentificationsByPeptide(String sequence) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("sequence", sequence));
        return (List<Identification>) criteria.list();
    }

    @Override
    public List<Identification> findAllIdentificationsByAssayAndPeptide(String experiment, String sequence) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("sequence", sequence));
        criteria.add(Restrictions.eq("assay", experiment));
        return (List<Identification>) criteria.list();
    }

    @Override
    public List<Identification> findAllFilteredIdentifications(HashMap<String, Object> requestedCriteria) {
        Criteria criteria = createEntityCriteria();
        for (Entry<String, Object> aCriterion : requestedCriteria.entrySet()) {
            if (aCriterion.getKey().equalsIgnoreCase("confidence")) {
                criteria.add(Restrictions.ge(aCriterion.getKey(), aCriterion.getValue()));
            } else {
                criteria.add(Restrictions.eq(aCriterion.getKey(), aCriterion.getValue()));
            }
        }
        return (List<Identification>) criteria.list();
    }

    @Override
    public List<Identification> findAllIdentifications() {
        Criteria criteria = createEntityCriteria();
        return (List<Identification>) criteria.list();
    }
}
