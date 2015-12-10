package com.compomics.respinonline.springmvc.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SPECTRUM_METADATA")
public class SpectrumMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 50)
    @Column(name = "ASSAY", nullable = false)
    private String assay;

    @Size(min = 3, max = 50)
    @Column(name = "SPECTRUM_ID", nullable = false)
    private String spectrumID;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "PRECURSOR_MZ", nullable = false)
    private BigDecimal precursorMz;

    @NotEmpty
    @Column(name = "FILE", unique = true, nullable = false)
    private String fileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssay() {
        return assay;
    }

    public void setAssay(String assay) {
        this.assay = assay;
    }

    public String getSpectrumID() {
        return spectrumID;
    }

    public void setSpectrumID(String spectrumID) {
        this.spectrumID = spectrumID;
    }

    public BigDecimal getPrecursorMz() {
        return precursorMz;
    }

    public void setPrecursorMz(BigDecimal precursorMz) {
        this.precursorMz = precursorMz;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

        
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.assay);
        hash = 29 * hash + Objects.hashCode(this.spectrumID);
        hash = 29 * hash + Objects.hashCode(this.fileName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpectrumMetadata other = (SpectrumMetadata) obj;
        if (!Objects.equals(this.assay, other.assay)) {
            return false;
        }
        if (!Objects.equals(this.spectrumID, other.spectrumID)) {
            return false;
        }
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        return true;
    }



}
