package com.compomics.respinonline.springmvc.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "IDENTIFICATION")
public class Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "ASSAY", nullable = false)
    private String assay;


    @Column(name = "SPECTRUM_ID", nullable = false)
    private String spectrumID;

    @NotNull
    @Column(name = "CONFIDENCE", nullable = false)
    private BigDecimal confidence;

    @NotEmpty
    @Column(name = "SEQUENCE", unique = true, nullable = false)
    private String sequence;

    public Identification() {
    }

       
    public Identification(String assay, String spectrumID, BigDecimal confidence, String sequence) {
        this.assay = assay;
        this.spectrumID = spectrumID;
        this.confidence = confidence;
        this.sequence = sequence;
    }

    
    
    
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

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    public String getSpectrumID() {
        return spectrumID;
    }

    public void setSpectrumID(String spectrumID) {
        this.spectrumID = spectrumID;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.assay);
        hash = 29 * hash + Objects.hashCode(this.spectrumID);
        hash = 29 * hash + Objects.hashCode(this.sequence);
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
        final Identification other = (Identification) obj;
        if (!Objects.equals(this.assay, other.assay)) {
            return false;
        }
        if (!Objects.equals(this.spectrumID, other.spectrumID)) {
            return false;
        }
        if (!Objects.equals(this.sequence, other.sequence)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Identification [id=" + id + ", assay=" + assay + ", spectrum="
                + spectrumID + ", confidence=" + confidence + ", sequence=" + sequence + "]";
    }

}
