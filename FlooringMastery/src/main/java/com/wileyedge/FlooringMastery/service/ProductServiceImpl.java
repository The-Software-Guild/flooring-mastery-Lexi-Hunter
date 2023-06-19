package com.wileyedge.FlooringMastery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wileyedge.FlooringMastery.dao.ProductDao;
import com.wileyedge.FlooringMastery.model.Product;

@Component
public class ProductServiceImpl implements ProductService{

	ProductDao dao;
	
	public ProductServiceImpl(ProductDao dao) {
		this.dao = dao;
		readProductFromFile();
	}
	
	public void readProductFromFile() {
		dao.readProductFromFile();
	}
	
	public List<Product> getProducts(){
		return dao.getProducts();
	}

	public BigDecimal getCostPerSquareFoot(String productType) {
		return dao.getCostPerSquareFoot(productType);
	}
	
	public BigDecimal getLabourCostPerSquareFoot(String productType) {
		return dao.getlabourCostPerSquareFoot(productType);
	}
	
	public boolean isValidProductType(String productType) {
	    return dao.getProducts().stream()
	            .anyMatch(product -> product.getProductType().equalsIgnoreCase(productType));
	}

	
}
