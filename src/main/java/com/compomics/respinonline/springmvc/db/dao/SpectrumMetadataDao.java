package com.compomics.respinonline.springmvc.db.dao;

import com.compomics.respinonline.springmvc.model.SpectrumMetadata;
import java.util.List;

public interface SpectrumMetadataDao {

    SpectrumMetadata findById(int id);

    void saveIdentification(SpectrumMetadata spectrumMetadata);

    public List<SpectrumMetadata> findAllSpectrumMetadata();

    public List<SpectrumMetadata> findAllSpectrumMetadataByExperiment(String experiment);

    public List<SpectrumMetadata> findAllSpectrumMetadataBySpectrumID(String spectrumID);

    public SpectrumMetadata findSpectrumByAssayAndSpectrumID(String spectrum_id, String experiment);
}
