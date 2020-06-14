package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.newOutputStream;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import java.util.Properties;

/**
 * TaxesManager gets the tax rate from a province.
 *
 * @author Johnny Lin
 */
public class TaxesManager {

    private static TaxesManager taxesManager;

    /**
     * private constructor
     */
    private TaxesManager() {
    }

    /**
     * Gets singleton instance of the TaxesManager
     * @return 
     */
    public static TaxesManager getInstance() {
        if (taxesManager == null) {
            taxesManager = new TaxesManager();
        }
        return taxesManager;
    }

    /**
     * Gets the tax rate for a province. If there is bad input, the default is
     * QC (Quebec).
     *
     * @param province province
     * @return tax rate
     */
    public double getTax(String province) {
//        LOG.debug("Attempting to load from file.");
        if (province == null) {
            province = "QC";
        }
        
        double taxRate = 0.0;
        
        try {
            Properties provinceCodes = new Properties();
            Path provinceCodesPath = get("src/main/resources", "province_code.properties");
            Properties taxRates = new Properties();
            Path taxRatesPath = get("src/main/resources", "taxes.properties");
            if (Files.exists(provinceCodesPath) && Files.exists(taxRatesPath)) {

//            LOG.debug("File exists! Will load it.");
//            cb = new ConfigBean();
                try (InputStream propFileStream = newInputStream(provinceCodesPath);) {
                    provinceCodes.load(propFileStream);
                }
                try (InputStream propFileStream = newInputStream(taxRatesPath);) {
                    taxRates.load(propFileStream);
                }
                String provinceCode = provinceCodes.getProperty(province.toUpperCase(), "QC");
                taxRate = Double.parseDouble(taxRates.getProperty(provinceCode));
            }
        } catch (IOException ioe) {
//            System.out.println("Province not found (" + province +"), using default QC");
        }
//        if (cb == null) LOG.debug("Configuration is not configured.");
        return taxRate;
    }

}
