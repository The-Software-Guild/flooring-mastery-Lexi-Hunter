package com.wileyedge.FlooringMastery.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {

	private int orderNumber;
	private LocalDate date;
	private String customerName;
	private String state;
	private BigDecimal taxRate;
	private String productType;
	private BigDecimal area;
	private BigDecimal costPerSquareFoot;
	private BigDecimal labourCostPerSquareFoot;
	private BigDecimal materialCost;
	private BigDecimal labourCost;
	private BigDecimal tax;
	private BigDecimal total;
	
	private static int orderCount = 1;
	
	public Order() {
		
	}
	
	public Order(LocalDate orderDate, String customerName, String state, BigDecimal taxRate, 
			String productType, BigDecimal area, BigDecimal costPerSquareFoot, BigDecimal labourCostPerSquareFoot) {
		orderNumber = orderCount++;
		this.date = orderDate;
		this.customerName = customerName;
		this.state = state;
		this.taxRate = taxRate;
		this.productType = productType;
		this.area = area;
		this.costPerSquareFoot = costPerSquareFoot;
		this.labourCostPerSquareFoot = labourCostPerSquareFoot;
	}

	public void setDerivedValues() {
		
		calculateMaterialCost();
		calculateLabourCost();
		calculateTax();
		calculateTotal();
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
		
		// Update orderCount
		orderCount = Math.max(orderCount, orderNumber + 1);
		
	}
	
	public void calculateMaterialCost() {
        this.materialCost = area.multiply(this.costPerSquareFoot);
    }

    public void calculateLabourCost() {
        this.labourCost = area.multiply(this.labourCostPerSquareFoot);
    }

    public void calculateTax() {
        BigDecimal taxRateDecimal = this.taxRate.divide(BigDecimal.valueOf(100));
        this.tax = (this.materialCost.add(this.labourCost)).multiply(taxRateDecimal);
    }

    public void calculateTotal() {
        this.total = this.materialCost.add(this.labourCost).add(this.tax);
    }
    
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public static int getOrderCount() {
		return orderCount;
	}
	public static void setOrderCount(int orderCount) {
		Order.orderCount = orderCount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public BigDecimal getArea() {
		return area;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
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
	public BigDecimal getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(BigDecimal materialCost) {
		this.materialCost = materialCost;
	}
	public BigDecimal getLabourCost() {
		return labourCost;
	}
	public void setLabourCost(BigDecimal labourCost) {
		this.labourCost = labourCost;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", date=" + date + ", customerName=" + customerName + ", state="
				+ state + ", taxRate=" + taxRate + ", productType=" + productType + ", area=" + area
				+ ", costPerSquareFoot=" + costPerSquareFoot + ", labourCostPerSquareFoot=" + labourCostPerSquareFoot
				+ ", materialCost=" + materialCost + ", labourCost=" + labourCost + ", tax=" + tax + ", total=" + total
				+ "]";
	}
}
