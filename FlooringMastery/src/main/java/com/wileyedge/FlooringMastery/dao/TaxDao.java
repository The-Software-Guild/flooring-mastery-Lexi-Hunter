package com.wileyedge.FlooringMastery.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wileyedge.FlooringMastery.model.Tax;

@Component
public class TaxDao {

	private final String TAX_FILE = "C:/C353/FlooringMastery/SampleFileData/Data/Taxes.txt";
    private List<Tax> taxes = new ArrayList<>();
    
    public List<Tax> getTaxRates(){
    	return taxes;
    }
    
    public BigDecimal getTaxRate(String state) {
        for (Tax tax : taxes) {
            if (tax.getStateAbbreviation().equalsIgnoreCase(state)) {
                return tax.getTaxRate();
            }
        }
        return null;
    }
    
    public void readTaxFromFile() {
        Path filePath = Paths.get(TAX_FILE);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;

            // Skip header line
            br.readLine();

            // Read the file line by line
            while ((line = br.readLine()) != null) {
                // Parse the line to create a Tax object
                Tax tax = parseTax(line);

                // Add the tax to the list
                taxes.add(tax);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Tax parseTax(String line) {
        String[] parts = line.split(",");

        Tax tax = new Tax();
        tax.setStateAbbreviation(parts[0]);
        tax.setStateName(parts[1]);
        tax.setTaxRate(new BigDecimal(parts[2]));

        return tax;
    }
    
    public boolean isValidState(String state) {
        return taxes.stream().anyMatch(t -> t.getStateAbbreviation().equalsIgnoreCase(state));
    }
	
}
