package com.wileyedge.FlooringMastery.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wileyedge.FlooringMastery.model.Product;

@Component
public class ProductDaoImpl implements ProductDao{
	List<Product> products = new ArrayList<>();
	
	public List<Product> getProducts(){
		return products;
	}
	
	public BigDecimal getCostPerSquareFoot(String productType) {
        for (Product product : products) {
            if (product.getProductType().equalsIgnoreCase(productType)) {
                return product.getCostPerSquareFoot();
            }
        }
        return null;
    }
	
	public BigDecimal getlabourCostPerSquareFoot(String productType) {
        for (Product product : products) {
            if (product.getProductType().equalsIgnoreCase(productType)) {
                return product.getLabourCostPerSquareFoot();
            }
        }
        return null;
    }
	
	public void readProductFromFile() {
	    // Create the file path
	    String filePath = "C:/C353/FlooringMastery/SampleFileData/Data/Products.txt";

	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        // Skip the header line
	        reader.readLine();

	        String line;
	        while ((line = reader.readLine()) != null) {
	            Product product = parseProduct(line);
	            products.add(product);
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading the product file");
	        e.printStackTrace();
	    }
	}

	private Product parseProduct(String line) {
	    String[] parts = line.split(",");

	    Product product = new Product();
	    product.setProductType(parts[0]);
	    product.setCostPerSquareFoot(new BigDecimal(parts[1]));
	    product.setLabourCostPerSquareFoot(new BigDecimal(parts[2]));

	    return product;
	}
	
}
