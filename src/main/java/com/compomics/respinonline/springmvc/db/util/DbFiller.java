package com.compomics.respinonline.springmvc.db.util;

import com.compomics.respinonline.springmvc.configuration.OfflineConfig;
import com.compomics.respinonline.springmvc.db.service.IdentificationService;
import com.compomics.respinonline.springmvc.db.service.SpectrumService;
import com.compomics.respinonline.springmvc.model.Identification;
import com.compomics.respinonline.springmvc.model.Spectrum;
import com.compomics.util.io.json.JsonMarshaller;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kenneth
 */
@Component
public class DbFiller {

    public static void main(String[] args) throws Exception {
        new DbFiller().fill();
    }

    public void fill() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(OfflineConfig.class);
        SpectrumService spectrumService = context.getBean(SpectrumService.class);

        String assay = "11954";
        String spectrumID = "1923";
        
        IdentificationService idService = context.getBean(IdentificationService.class);

        Identification ident = new Identification(assay, spectrumID, new BigDecimal(99.2), "KENNETH");
        
        idService.saveIdentification(ident);
        
        Spectrum details = new Spectrum();
      
        details.setAssay(assay);
        details.setCharge(2);
        details.setFile("TEST_MOCK_YOU_NAME_IT.MGF");
        details.setPrecursor_intensity(1000.3);
        details.setPrecursor_mz(413.3);
        details.setSpectrum_id(spectrumID);
        details.setMs2Peaks(getPeaks2DArray());

        spectrumService.saveSpectrum(details);

        Spectrum findBySpectrumIdAndExperiment = spectrumService.findBySpectrumIdAndExperiment(details.getSpectrum_id(), details.getAssay());

        System.out.println(findBySpectrumIdAndExperiment.getMs2Peaks());
    }

    private String getPeaks2DArray() {
        return new JsonMarshaller().toJson(mzValues);
    }

    private static final double[][] mzValues = new double[][]{
        new double[]{519.081177, 4.015530},
        new double[]{520.527100, 9.813676},
        new double[]{521.991821, 26.945614},
        new double[]{523.370972, 12.161747},
        new double[]{524.278442, 3.381213},
        new double[]{525.198730, 3.853895},
        new double[]{526.070801, 3.820176},
        new double[]{527.147827, 25.483242},
        new double[]{528.342773, 11.368365},
        new double[]{529.232910, 8.339369},
        new double[]{530.080994, 7.815114},
        new double[]{532.809937, 6.275185},
        new double[]{534.071167, 8.151394},
        new double[]{535.136719, 8.521399},
        new double[]{536.179688, 19.452126},
        new double[]{537.128174, 10.514593},
        new double[]{538.413452, 18.023069},
        new double[]{544.333984, 9.910419},
        new double[]{545.273132, 11.143578},
        new double[]{551.329102, 8.597209},
        new double[]{552.420044, 22.845009},
        new double[]{553.281860, 9.001399},
        new double[]{555.260742, 15.997867},
        new double[]{559.417725, 4.437509},
        new double[]{562.404053, 405.051514},
        new double[]{563.498840, 48.098305},
        new double[]{565.600952, 3.387759},
        new double[]{566.737915, 5.851104},
        new double[]{569.473877, 12.515773},
        new double[]{570.589050, 20.032322},
        new double[]{571.230835, 6.776375},
        new double[]{572.278748, 19.095318},
        new double[]{573.149292, 17.406981},
        new double[]{576.461426, 14.950231},
        new double[]{579.109985, 10.360884},
        new double[]{581.514038, 14.560740},
        new double[]{584.206909, 30.957813},
        new double[]{585.478271, 3.959915},
        new double[]{588.279968, 2.945799},
        new double[]{589.511108, 10.556177},
        new double[]{590.388306, 26.146528},
        new double[]{591.337036, 14.897098},
        new double[]{592.107544, 4.106139},
        new double[]{593.386719, 3.813607},
        new double[]{594.614868, 11.580721},
        new double[]{596.236755, 24.995085},
        new double[]{597.317261, 22.972145},
        new double[]{597.943237, 9.280321},
        new double[]{599.349854, 14.812905},
        new double[]{603.377197, 5.381608},
        new double[]{605.454956, 9.477353},
        new double[]{606.883667, 21.293558},
        new double[]{608.134216, 24.021933},
        new double[]{609.302917, 3.112401},
        new double[]{611.359680, 70.052658}};

}
