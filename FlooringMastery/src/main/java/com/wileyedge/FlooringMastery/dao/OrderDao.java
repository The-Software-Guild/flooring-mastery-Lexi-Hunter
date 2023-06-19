package com.wileyedge.FlooringMastery.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wileyedge.FlooringMastery.model.Order;

@Component
public class OrderDao {

	private static final String FILE_PATH = "C:/C353/FlooringMastery/SampleFileData/Orders/";
    private static final String FILE_PREFIX = "Orders_";
    private static final String FILE_SUFFIX = ".txt";
	
	private List<Order> orders = new ArrayList();
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public List<Order> getAllOrders() {
		return orders;
	}
	
	public List<Order> getOrdersByDate(LocalDate date) {
		List<Order> selectedOrders = orders.stream().filter(d -> d.getDate().equals(date)).collect(Collectors.toList());
		return selectedOrders;
	}
	
	public Order getOrderByOrderNumber(int orderNumber) {
		Order order = orders.stream().filter(d -> d.getOrderNumber() == orderNumber).findFirst().orElse(null);
		return order;
	}
	
	public void readOrdersFromFile() {
        // Specify the path where the order files are stored
        Path dir = Paths.get("C:/C353/FlooringMastery/SampleFileData/Orders");
        
        // Iterate over all files in the directory
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path entry: stream) {
                // Read each file and add the orders to the list
                readOrderFile(entry);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
	
	private void readOrderFile(Path filePath) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
		
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            
            // Skip header line
            br.readLine();
            
            // Extract the date from the filename
            String fileName = filePath.getFileName().toString();
            String dateStr = fileName.substring(fileName.indexOf('_') + 1, fileName.indexOf('.'));
            LocalDate date = LocalDate.parse(dateStr, formatter);
            
            // Read the file line by line
            while ((line = br.readLine()) != null) {
                // Parse the line to create an Order object
                Order order = parseOrder(line);
                order.setDate(date);
                
                // Add the order to the list
                orders.add(order);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
	
	private Order parseOrder(String line) {
	    String[] parts = line.split(",");
	    
	    Order order = new Order();
	    order.setOrderNumber(Integer.parseInt(parts[0]));
	    order.setCustomerName(parts[1]);
	    order.setState(parts[2]);
	    order.setTaxRate(new BigDecimal(parts[3]));
	    order.setProductType(parts[4]);
	    order.setArea(new BigDecimal(parts[5]));
	    order.setCostPerSquareFoot(new BigDecimal(parts[6]));
	    order.setLabourCostPerSquareFoot(new BigDecimal(parts[7]));
	    order.setMaterialCost(new BigDecimal(parts[8]));
	    order.setLabourCost(new BigDecimal(parts[9]));
	    order.setTax(new BigDecimal(parts[10]));
	    order.setTotal(new BigDecimal(parts[11]));
	    
	    return order;
	}

	public void removeOrder(Order order) {
	    if (orders.contains(order)) {
	        orders.remove(order);
	    }
	}
	
}





