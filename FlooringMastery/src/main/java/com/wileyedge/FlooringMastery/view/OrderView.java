package com.wileyedge.FlooringMastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.wileyedge.FlooringMastery.model.Order;
import com.wileyedge.FlooringMastery.model.Product;

@Component
public class OrderView {
	
	private UserIO io;
	
	public OrderView(UserIO io) {
		this.io = io;
	}
	
	public void showMenu() {
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\r\n" + 
				"  * <<Flooring Program>>\r\n" + 
				"  * 1. Display Orders\r\n" + 
				"  * 2. Add an Order\r\n" + 
				"  * 3. Edit an Order\r\n" + 
				"  * 4. Remove an Order\r\n" + 
				"  * 5. Export All Data\r\n" + 
				"  * 6. Quit\r\n" + 
				"  *\r\n" + 
				"  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	}

	public int promptForInt(int min, int max) {
		return io.getInt(min, max);
	}
	
	public LocalDate promptForDate() {
		return io.getDate();
	}
	
	public LocalDate promptForFutureDate() {
		return io.getFutureDate();
	}
	
	public String promptForCustomerName() {
		return io.getCustomerName();
	}
	
	public String promptForState() {
	    return io.getString("Please enter a state abbreviation:").trim();
	}
	
	public String promptForProductType(List<Product> products) {
	    System.out.println("Available products:");
	    for (Product product : products) {
	        System.out.printf("Type: %s, Cost per sq ft: $%.2f, Labor cost per sq ft: $%.2f\n", 
	                          product.getProductType(), product.getCostPerSquareFoot(), product.getLabourCostPerSquareFoot());
	    }

	    return io.getString("Please enter a product type from the list above:").trim();
	}
	
	public int promptForOrderNumber() {
		System.out.println("Enter the order number: ");
		return io.getInt();
	}
	
	public void displayOrders(List<Order> orders) {
		for(Order o: orders) {
			System.out.println(o);
		}
	}
	
	public void displayUnknownCommand() {
		// TODO Auto-generated method stub
		
	}

	public void displayExitBanner() {
		// TODO Auto-generated method stub
		
	}

	public BigDecimal promptForArea(BigDecimal min) {
	    System.out.println("Please enter the area for the order (Minimum order size is 100 sq ft):");
	    return io.getBigDecimal(min);
	}

	public void displayOrderSummary(Order order) {
        System.out.println("\nOrder Summary:");
        System.out.println("Order Date: " + order.getDate());
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("State: " + order.getState());
        System.out.println("Product Type: " + order.getProductType());
        System.out.println("Area: " + order.getArea());
        System.out.println("Material Cost: " + order.getMaterialCost());
        System.out.println("Labor Cost: " + order.getLabourCost());
        System.out.println("Tax: " + order.getTax());
        System.out.println("Total: " + order.getTotal());
    }
	
	public Boolean promptForUserConfirmation(String message) {
        System.out.println(message);
        Boolean userResponse = io.getBoolean();
        return userResponse;
    }

	public String promptForCustomerNameWithDefault(String currentCustomerName) {
	    String response = io.getString("Enter customer name (" + currentCustomerName + "):");

	    // If response is empty, return currentCustomerName, otherwise return the response
	    return response.isEmpty() ? currentCustomerName : response;
	}

	public String promptForStateWithDefault(String currentState) {
	    String response = io.getString("Enter state abbreviation (" + currentState + "):");

	    // If response is empty, return currentState, otherwise return the response
	    return response.isEmpty() ? currentState : response;
	}

	public String promptForProductTypeWithDefault(List<Product> products, String currentProductType) {
	    System.out.println("Available products:");
	    for (Product product : products) {
	        System.out.printf("Type: %s, Cost per sq ft: $%.2f, Labor cost per sq ft: $%.2f\n", 
	                          product.getProductType(), product.getCostPerSquareFoot(), product.getLabourCostPerSquareFoot());
	    }
	    
	    String response = io.getString("Please enter a product type from the list above (" + currentProductType + "):").trim();
	    return response.isEmpty() ? currentProductType : response;
	}

	public BigDecimal promptForAreaWithDefault(BigDecimal min, BigDecimal currentArea) {
	    System.out.println("Please enter the area for the order (Minimum order size is 100 sq ft, current: " + currentArea + "):");
	    BigDecimal response = io.getBigDecimalWithDefault(min, currentArea);
	    return response == null ? currentArea : response;
	}

}
