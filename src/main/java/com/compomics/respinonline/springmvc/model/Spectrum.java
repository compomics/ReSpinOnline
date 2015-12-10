package com.compomics.respinonline.springmvc.model;

import com.compomics.util.io.json.JsonMarshaller;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SPECTRUM")
public class Spectrum {

    //hibernate
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Size(min = 3, max = 50)
    @Column(name = "SPECTRUM", nullable = false)
    private String spectrum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(String spectrum) {
        this.spectrum = spectrum;
    }

    //POJO
    private SpectrumMetadata metadata;
    private double[][] peaks;

    public SpectrumMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(SpectrumMetadata metadata) {
        this.metadata = metadata;
    }

    public double[][] getPeaks() {
        return peaks;
    }

    public void setPeaks(double[][] peaks) {
        this.peaks = peaks;
    }

    public Spectrum(SpectrumMetadata metadata, double[][] peaks) {
        this.metadata = metadata;
        this.peaks = peaks;
        this.spectrum = this.toJson();
    }

    public Spectrum(int id, SpectrumMetadata metadata, double[][] peaks) {
        this.id = id;
        this.metadata = metadata;
        this.peaks = peaks;
        this.spectrum = this.toJson();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        return hash;
    }

    private String toJson() {
        JsonMarshaller marshaller = new JsonMarshaller();
        return marshaller.toJson(this);
    }

    @Override
    public String toString(){
        return toJson();
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
        final Spectrum other = (Spectrum) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
