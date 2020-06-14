/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilitiesTests;

import static org.junit.Assert.*;
import org.junit.Test;
import utility.TaxesManager;

/**
 *
 * @author Monoxian
 */
public class TaxesManagerTest {
    
    @Test
    public void getQuebecTax(){
        double qc_tax = TaxesManager.getInstance().getTax("quebec");
        assertTrue(qc_tax == 0.14975);
    }
    
    @Test
    public void getNullTax(){
        double tax = TaxesManager.getInstance().getTax(null);
        assertTrue(tax == 0.14975);
    }
    
    @Test
    public void getQCTax(){
        double tax = TaxesManager.getInstance().getTax("QC");
        assertTrue(tax == 0.14975);
    }
    
    @Test
    public void getONTax(){
        double tax = TaxesManager.getInstance().getTax("ON");
        assertTrue(tax == 0.13);
    }
    
    @Test
    public void getOntarioTax(){
        double tax = TaxesManager.getInstance().getTax("OnTaRiO");
        assertTrue(tax == 0.13);
    }
    @Test
    public void getEmptyStringTax(){
        double tax = TaxesManager.getInstance().getTax("");
        assertTrue(tax == 0.14975);
    }
}
