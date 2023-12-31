package com.wileyedge.FlooringMastery.model;

import java.math.BigDecimal;

public class Product {

	private String productType;
	private BigDecimal costPerSquareFoot;
	private BigDecimal labourCostPerSquareFoot;
	
	public Product() {
		
	}
	
	public Product(String productType, BigDecimal costPerSquareFoot, BigDecimal labourCostPerSquareFoot) {
		super();
		this.productType = productType;
		this.costPerSquareFoot = costPerSquareFoot;
		this.labourCostPerSquareFoot = labourCostPerSquareFoot;
	}

	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public BigDecimal getCostPerSquareFoot() {
		return costPerSquareFoot;
	}
	
	public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
		this.costPerSquareFoot = costPerSquareFoot;
	}
	
	public BigDecimal getLabourCostPerSquareFoot() {
		return labourCostPerSquareFoot;
	}
	
	public void setLabourCostPerSquareFoot(BigDecimal labourCostPerSquareFoot) {
		this.labourCostPerSquareFoot = labourCostPerSquareFoot;
	}
	
	@Override
	public String toString() {
		return "Product [productType=" + productType + ", costPerSquareFoot=" + costPerSquareFoot
				+ ", labourCostPerSquareFoot=" + labourCostPerSquareFoot + "]";
	}
}
