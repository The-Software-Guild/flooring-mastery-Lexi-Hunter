package com.wileyedge.FlooringMastery.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.wileyedge.FlooringMastery.model.Order;
import com.wileyedge.FlooringMastery.model.Product;
import com.wileyedge.FlooringMastery.service.OrderService;
import com.wileyedge.FlooringMastery.service.ProductService;
import com.wileyedge.FlooringMastery.service.TaxService;
import com.wileyedge.FlooringMastery.view.OrderView;
import com.wileyedge.FlooringMastery.view.UserIO;

@Controller
public class OrderControllerImpl implements OrderController{

	private OrderView view;
	private OrderService orderService;
	private TaxService taxService;
	private ProductService productService;
	
	public OrderControllerImpl(OrderView view, OrderService orderService, TaxService taxService,
			ProductService productService, UserIO io) {
        this.view = view;
        this.orderService = orderService;
        this.taxService = taxService;
        this.productService = productService;
    }
	
	@Override
	public void run() {
		
		boolean keepGoing = true;
        int menuSelection = 0;
        
        while (keepGoing) {
        	view.showMenu();
        	menuSelection = view.promptForInt(1, 6);
        	
        	switch (menuSelection) {
	            case 1:
	                displayOrders();
	                break;
	            case 2:
	                addOrder();
	                break;
	            case 3:
	                editOrder();
	                break;
	            case 4:
	                removeOrder();
	                break;
	            case 5:
	                exportAllData();
	                break;
	            case 6:
	                keepGoing = false;
	                break;
	            default:
	                unknownCommand();
	        }
        }
        exitMessage();
            
	}
	
	@Override
	public void displayOrders() {
		LocalDate date = view.promptForDate();
		List<Order> selectedOrders = orderService.getOrdersByDate(date);
		view.displayOrders(selectedOrders);
    }

	@Override
	public void addOrder() {
		
		LocalDate orderDate = view.promptForFutureDate();
		String customerName = view.promptForCustomerName();
		String state;
		
		while (true) {
	        state = view.promptForState();
	        if (taxService.isValidState(state)) {
	            break;
	        } else {
	            System.out.println("We cannot sell in the entered state. Please enter a valid state abbreviation:");
	        }
	    }
		
		BigDecimal taxRate = taxService.getTaxRate(state);

		List<Product> products = productService.getProducts();
	    String productType;
	    while (true) {
	        productType = view.promptForProductType(products);
	        if (productService.isValidProductType(productType)) {
	            break;
	        } else {
	            System.out.println("Invalid product type. Please enter a product type from the list:");
	        }
	    }
	    
	    BigDecimal costPerSquareFoot = productService.getCostPerSquareFoot(productType);
	    BigDecimal labourCostPerSquareFoot = productService.getLabourCostPerSquareFoot(productType);
	    
	    BigDecimal area = view.promptForArea(new BigDecimal(100));
	    
	    Order newOrder = new Order(orderDate, customerName, state, taxRate, productType,
	    		area, costPerSquareFoot, labourCostPerSquareFoot);
	    
	    newOrder.setDerivedValues();
	    
	    view.displayOrderSummary(newOrder);
	    
	    Boolean userConfirmation = view.promptForUserConfirmation("Do you want to place the order?");
	    if(userConfirmation) {
	        orderService.addOrder(newOrder);
	        System.out.println("Order has been added successfully.");
	    } else {
	    	Order.setOrderCount(Order.getOrderCount()-1);
	        System.out.println("Order was not added. Returning to the main menu.");
	    }
	    
    }

	@Override
	public void editOrder() {

	    LocalDate date = view.promptForDate();
	    int orderNumber = view.promptForOrderNumber();

	    Order order = orderService.getOrderByOrderNumber(orderNumber);

	    if(order != null && order.getDate().equals(date)) {
	        System.out.println("Editing order...");

	        // Customer name
	        String currentCustomerName = order.getCustomerName();
	        String newCustomerName = view.promptForCustomerNameWithDefault(currentCustomerName);

	        // State
	        String currentState = order.getState();
	        String newState = view.promptForStateWithDefault(currentState);
	        if (!newState.isEmpty()) {
	            if (taxService.isValidState(newState)) {
	                order.setState(newState);
	                BigDecimal newTaxRate = taxService.getTaxRate(newState);
	                order.setTaxRate(newTaxRate);
	            } else {
	                System.out.println("State entered is not valid. Existing state remains unchanged.");
	            }
	        }
	        
	        BigDecimal newTaxRate = taxService.getTaxRate(newState);

	        
	        // Product type
	        List<Product> products = productService.getProducts();
	        
	        String currentProductType = order.getProductType();
	        String newProductType = view.promptForProductTypeWithDefault(products, currentProductType);
	        
	        BigDecimal currentCostPerSquareFoot = productService.getCostPerSquareFoot(currentProductType);
	        BigDecimal currentLabourCostPerSquareFoot = productService.getLabourCostPerSquareFoot(currentProductType);
	        
	        BigDecimal newCostPerSquareFoot = null;
	        BigDecimal newLabourCostPerSquareFoot = null;
	        
	        if (!newProductType.isEmpty()) {
	            if (productService.isValidProductType(newProductType)) {
	                order.setProductType(newProductType);
	                newCostPerSquareFoot = productService.getCostPerSquareFoot(newProductType);
	                newLabourCostPerSquareFoot = productService.getLabourCostPerSquareFoot(newProductType);
	             
	            } else {
	                System.out.println("Product type entered is not valid. Existing product type remains unchanged.");
	            }
	        }
	        
	        // Area
	        BigDecimal currentArea = order.getArea();
	        BigDecimal newArea = view.promptForAreaWithDefault(new BigDecimal(100), currentArea);
	        
	        Order changedOrder = new Order(order.getDate(), newCustomerName, newState, newTaxRate, newProductType,
		    		newArea, newCostPerSquareFoot, newLabourCostPerSquareFoot);
		    
	        changedOrder.setDerivedValues();

	        view.displayOrderSummary(changedOrder);

	        Boolean userConfirmation = view.promptForUserConfirmation("Do you want to place the order?");
	        if(userConfirmation) {
	            orderService.updateOrder(order, changedOrder);
	            System.out.println("Order has been updated successfully.");
	        } else {
	            System.out.println("Order was not updated. Returning to the main menu.");
	        }
	        
	    } else {
	        System.out.println("No order found on that date with the provided order number. Returning to the main menu.");
	    }
	}

	@Override
	public void removeOrder() {
	    // Ask the user for the date and order number
	    LocalDate date = view.promptForDate();
	    int orderNumber = view.promptForOrderNumber();
	    
	    // Fetch the order from the service
	    Order order = orderService.getOrderByOrderNumber(orderNumber);
	    
	    if(order != null) {
	        // Display the order information
	        view.displayOrderSummary(order);
	        
	        // Ask the user if they are sure they want to delete the order
	        boolean userConfirmation = view.promptForUserConfirmation("Are you sure you want to delete this order? (Y/N)");

	        if(userConfirmation) {
	            // Remove the order from the list
	            orderService.removeOrder(order);
	            System.out.println("Order successfully removed.");
	        } else {
	            System.out.println("Order was not removed.");
	        }
	    } else {
	        System.out.println("Order not found.");
	    }
	}

	@Override
	public void exportAllData() {
	    List<Order> allOrders = orderService.getAllOrders();

	    // Define the formatter for date
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

	    try (PrintWriter writer = new PrintWriter("C:\\C353\\FlooringMastery\\SampleFileData\\Backup\\DataExport.txt")) {
	        
	        // Print the header
	        writer.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LabourCostPerSquareFoot,MaterialCost,LabourCost,Tax,Total,OrderDate");

	        // Loop through each order and print to file
	        for (Order order : allOrders) {
	            writer.printf("%d,%s,%s,%.2f,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%s\n",
	                    order.getOrderNumber(),
	                    order.getCustomerName(),
	                    order.getState(),
	                    order.getTaxRate(),
	                    order.getProductType(),
	                    order.getArea(),
	                    order.getCostPerSquareFoot(),
	                    order.getLabourCostPerSquareFoot(),
	                    order.getMaterialCost(),
	                    order.getLabourCost(),
	                    order.getTax(),
	                    order.getTotal(),
	                    order.getDate().format(formatter));
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("Error writing to file: " + e.getMessage());
	    }
	}

	@Override
    public void unknownCommand() {
        view.displayUnknownCommand();
    }

	@Override
    public void exitMessage() {
        view.displayExitBanner();
    }
}















