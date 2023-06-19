package com.wileyedge.FlooringMastery.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wileyedge.FlooringMastery.dao.OrderDao;
import com.wileyedge.FlooringMastery.model.Order;

@Component
public class OrderService {
	
	private OrderDao dao;
	
	public OrderService(OrderDao dao) {
		this.dao = dao;
		readOrdersFromFile();
	}
	
	public List<Order> getOrdersByDate(LocalDate date) {
		
		return dao.getOrdersByDate(date);
	}
	
	public List<Order> getAllOrders(){
		return dao.getAllOrders();
	}
	
	public Order getOrderByOrderNumber(int orderNumber) {
		return dao.getOrderByOrderNumber(orderNumber);
	}
	
	public void readOrdersFromFile() {
		dao.readOrdersFromFile();
	}

	public void addOrder(Order order) {
		dao.addOrder(order);
	}

	public void updateOrder(Order order, Order changedOrder) {
	    order.setDate(changedOrder.getDate());
	    order.setCustomerName(changedOrder.getCustomerName());
	    order.setState(changedOrder.getState());
	    order.setTaxRate(changedOrder.getTaxRate());
	    order.setProductType(changedOrder.getProductType());
	    order.setArea(changedOrder.getArea());
	    order.setCostPerSquareFoot(changedOrder.getCostPerSquareFoot());
	    order.setLabourCostPerSquareFoot(changedOrder.getLabourCostPerSquareFoot());
	    order.setMaterialCost(changedOrder.getMaterialCost());
	    order.setLabourCost(changedOrder.getLabourCost());
	    order.setTax(changedOrder.getTax());
	    order.setTotal(changedOrder.getTotal());
	}

	public void removeOrder(Order order) {
		dao.removeOrder(order);		
	}

}
