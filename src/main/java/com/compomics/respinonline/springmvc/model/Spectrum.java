package com.compomics.respinonline.springmvc.model;

/**
 *
 * @author Kenneth
 */
import com.compomics.util.io.json.JsonMarshaller;
import com.google.gson.reflect.TypeToken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "SPECTRUM")
public class Spectrum {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "ms2Peaks")
    @Type(type = "text")
    private String ms2Peaks;

    @Column(name = "assay")
    private String assay;

    @Column(name = "spectrum_id")
    private String spectrum_id;

    @Column(name = "file")
    private String file;

    @Column(name = "charge")
    private int charge;

    @Column(name = "precursor_mz")
    private double precursor_mz;

    @Column(name = "precursor_intensity")
    private double precursor_intensity;
    
    public Spectrum() {

    }

    public Spectrum(String peaks) {
        this.ms2Peaks = peaks;
    }

    public Spectrum(double[][] peaks) {
        setPeaks2DArray(peaks);
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double[][] getPeaks2DArray() {
        return (double[][]) new JsonMarshaller().fromJson(new TypeToken<double[][]>() {
        }.getType(), ms2Peaks);
    }

    private void setPeaks2DArray(double[][] ms2Peaks) {
        this.ms2Peaks = new JsonMarshaller().toJson(ms2Peaks);
    }

    public void setMs2Peaks(String ms2Peaks) {
        this.ms2Peaks = ms2Peaks;
    }

    public String getMs2Peaks() {
        return this.ms2Peaks;
    }

    public String getAssay() {
        return assay;
    }

    public void setAssay(String assay) {
        this.assay = assay;
    }

    public String getSpectrum_id() {
        return spectrum_id;
    }

    public void setSpectrum_id(String spectrum_id) {
        this.spectrum_id = spectrum_id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public double getPrecursor_mz() {
        return precursor_mz;
    }

    public void setPrecursor_mz(double precursor_mz) {
        this.precursor_mz = precursor_mz;
    }

    public double getPrecursor_intensity() {
        return precursor_intensity;
    }

    public void setPrecursor_intensity(double precursor_intensity) {
        this.precursor_intensity = precursor_intensity;
    }

    
    
}
