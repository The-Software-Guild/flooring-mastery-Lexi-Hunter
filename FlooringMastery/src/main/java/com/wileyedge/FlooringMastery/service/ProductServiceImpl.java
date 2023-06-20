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
	
	@Override
	public void readProductFromFile() {
		dao.readProductFromFile();
	}
	
	@Override
	public List<Product> getProducts(){
		return dao.getProducts();
	}

	@Override
	public BigDecimal getCostPerSquareFoot(String productType) {
		return dao.getCostPerSquareFoot(productType);
	}
	
	@Override
	public BigDecimal getLabourCostPerSquareFoot(String productType) {
		return dao.getlabourCostPerSquareFoot(productType);
	}
	
	@Override
	public boolean isValidProductType(String productType) {
	    return dao.getProducts().stream()
	            .anyMatch(product -> product.getProductType().equalsIgnoreCase(productType));
	}

	
}
