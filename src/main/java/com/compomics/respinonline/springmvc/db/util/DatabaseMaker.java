package com.compomics.respinonline.springmvc.db.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Kenneth
 */
public class DatabaseMaker {

    private final Properties applicationProperties = new Properties();
    private static final Logger LOGGER = Logger.getLogger(DatabaseMaker.class);

    public DatabaseMaker() throws IOException {
        init();
    }

    private void init() throws IOException {
        String resourceName = "application.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            applicationProperties.load(resourceStream);
        }
    }

    private String getIdentificationTable() {
        return "    CREATE TABLE IDENTIFICATION("
                + "    id INT NOT NULL auto_increment,"
                + "    spectrum_id VARCHAR(50) NOT NULL,"
                + "    assay VARCHAR(50) NOT NULL,"
                + "    sequence VARCHAR(100) NOT NULL,"
                + "    confidence DOUBLE NOT NULL,"
                + "    PRIMARY KEY (id)"
                + ");";
    }

    private String getSpectrumMetaDataTable() {
        return "    CREATE TABLE SPECTRUM_METADATA("
                + "    id INT NOT NULL auto_increment,"
                + "    spectrum_id VARCHAR(50) NOT NULL,"
                + "    assay VARCHAR(50) NOT NULL,"
                + "    file VARCHAR(100) NOT NULL,"
                + "    precursor_mz DOUBLE NOT NULL,"
                + "    PRIMARY KEY (id)"
                + ");";
    }

    private String getSpectrumTable() {
        return "    CREATE TABLE SPECTRUM("
                + "    id INT NOT NULL,"
                + "    SPECTRUM LONGTEXT NOT NULL,"
                + "    PRIMARY KEY (id)"
                + ");";
    }

    public void createDatabase() throws SQLException {
        try {
            Class.forName(applicationProperties.getProperty("jdbc.driverClassName"));
            try (Connection connection = DriverManager.getConnection(
                    applicationProperties.getProperty("jdbc.url"),
                    applicationProperties.getProperty("jdbc.username"),
                    applicationProperties.getProperty("jdbc.password"));
                    Statement st = connection.createStatement()) {
                String sql = "CREATE DATABASE IF NOT EXISTS " + applicationProperties.getProperty("jdbc.databaseName") + ";";
                st.executeUpdate("DROP DATABASE " + applicationProperties.getProperty("jdbc.databaseName"));
                LOGGER.info("Deleted database succesfully!");
                LOGGER.info("Creating database...");
                //Select the created / requested database
                st.executeQuery("USE " + applicationProperties.getProperty("jdbc.databaseName") + ";");
                st.executeUpdate(getIdentificationTable());
                st.executeUpdate(getSpectrumMetaDataTable());
                st.executeUpdate(getSpectrumTable());
                LOGGER.info("Created database succesfully!");
            }
        } catch (SQLException sqlException) {
            if (sqlException.getErrorCode() == 1007) {
                // Database already exists error
                LOGGER.info(sqlException.getMessage());
            } else {
                // Some other problems, e.g. Server down, no permission, etc
                LOGGER.error("Something went wrong while contacting the mySQL server", sqlException);
            }
        } catch (ClassNotFoundException e) {
            // No driver class found!
            LOGGER.error("Driver class could not be found", e);
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        new DatabaseMaker().createDatabase();

    }

}
