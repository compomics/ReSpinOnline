package com.compomics.respinonline.springmvc.db.util;

import com.compomics.respinonline.springmvc.configuration.OfflineConfig;
import com.compomics.respinonline.springmvc.db.service.IdentificationService;
import com.compomics.respinonline.springmvc.db.service.SpectrumService;
import com.compomics.respinonline.springmvc.model.Identification;
import com.compomics.respinonline.springmvc.model.Spectrum;
import com.compomics.util.io.json.JsonMarshaller;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Kenneth
 */
public class IdentificationSpoofer {

    private static HashMap<Identification, Spectrum> identificationMap = new HashMap<>();
    private static Random rand = new Random();
    private static JsonMarshaller marshaller = new JsonMarshaller();

    public static void main(String[] args) {
        int requestedSpectra = 20;
        for (int i = 0; i < requestedSpectra; i++) {
            System.out.println("Generating " + i + " th random identification");
            generateIdentification(i);
        }
    }

    public static void generateIdentification(int assay) {
        ApplicationContext context = new AnnotationConfigApplicationContext(OfflineConfig.class);
        SpectrumService spectrumService = context.getBean(SpectrumService.class);
        IdentificationService idService = context.getBean(IdentificationService.class);
        String sequence = getRandomSequence();
        System.out.println("RANDOM SEQUENCE IS " + sequence);
        for (int i = 0; i < 6; i++) {
            if (rand.nextBoolean()) {
                break;
            }
            Identification ident = new Identification();
            ident.setSequence(sequence);
            ident.setAssay(String.valueOf(assay));
            ident.setSpectrumID(String.valueOf(rand.nextInt(10000)));
            ident.setConfidence(new BigDecimal(100).multiply(new BigDecimal(rand.nextDouble())));
            idService.saveIdentification(ident);
            spectrumService.saveSpectrum(generateMatchingSpectrum(ident));
        }
    }

    private static Spectrum generateMatchingSpectrum(Identification ident) {
        Spectrum spectrum = new Spectrum();
        spectrum.setAssay(ident.getAssay());
        spectrum.setCharge(rand.nextInt(2) + 1);
        spectrum.setFile("TEST_" + RandomStringUtils.randomAlphabetic(5) + ".MGF");
        spectrum.setPrecursor_intensity(rand.nextDouble() * (750 + rand.nextInt(5000)));
        spectrum.setPrecursor_mz(rand.nextDouble() * (750 + rand.nextInt(2500)));
        spectrum.setSpectrum_id(ident.getSpectrumID());
        spectrum.setMs2Peaks(marshaller.toJson(generateRandomPeaks(spectrum)));
        return spectrum;
    }

    private static double[][] generateRandomPeaks(Spectrum spectrum) {
        int spectrumLength = 30 + rand.nextInt(1000);
        double[][] peaks = new double[spectrumLength][];
        for (int i = 0; i < spectrumLength; i++) {
            peaks[i] = new double[]{
                spectrum.getPrecursor_mz() * rand.nextDouble(),
                spectrum.getPrecursor_intensity() * rand.nextDouble()
            };
        }
        return peaks;
    }

    private static String getRandomSequence() {
        String randomSequence = RandomStringUtils.randomAlphabetic(5 + rand.nextInt(25));
        randomSequence = randomSequence.replaceAll("B", "A").replaceAll("O", "T").replaceAll("X", "Y");
        return randomSequence.toUpperCase();
    }

}
