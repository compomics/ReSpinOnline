package com.compomics.respinonline.springmvc.db.util;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author Kenneth
 */
public class DatabaseFiller {

    private static Properties applicationProperties = new Properties();

    public static void main(String[] args) throws Exception {
        String resourceName = "application.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            applicationProperties.load(resourceStream);
        }
        Class.forName(applicationProperties.getProperty("jdbc.driverClassName"));
        createIdentification("1", "KENNETH", "19212", new BigDecimal(99.2));
        createIdentification("1", "LENNART", "19213", new BigDecimal(98.8));
        createIdentification("2", "DAVY", "19214", new BigDecimal(98.3));
        createIdentification("3", "NIELS", " 19215", new BigDecimal(98.3));
    }

    private static void createIdentification(String assay, String sequence, String spectrumID, BigDecimal confidence) throws IOException, ClassNotFoundException, SQLException {

        try (Connection connection = DriverManager.getConnection(
                applicationProperties.getProperty("jdbc.url")+"/"+applicationProperties.getProperty("jdbc.databaseName"),
                applicationProperties.getProperty("jdbc.username"),
                applicationProperties.getProperty("jdbc.password"));
                PreparedStatement st = connection.prepareStatement("INSERT INTO IDENTIFICATION(assay,spectrum_id,sequence,confidence) values (?,?,?,?)");) {
            st.setString(1, assay);
            st.setString(2, spectrumID);
            st.setString(3, sequence);
            st.setDouble(4, confidence.doubleValue());
            st.executeUpdate();
        }
    }

}
