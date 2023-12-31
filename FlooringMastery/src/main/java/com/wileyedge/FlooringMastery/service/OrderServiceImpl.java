package com.wileyedge.FlooringMastery.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wileyedge.FlooringMastery.dao.OrderDao;
import com.wileyedge.FlooringMastery.model.Order;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderDao dao;
	
	@Autowired
	public OrderServiceImpl(OrderDao dao) {
		this.dao = dao;
		readOrdersFromFile();
	}
	
	@Override
	public List<Order> getOrdersByDate(LocalDate date) {
		
		return dao.getOrdersByDate(date);
	}
	
	@Override
	public List<Order> getAllOrders(){
		return dao.getAllOrders();
	}
	
	@Override
	public Order getOrderByOrderNumber(int orderNumber) {
		return dao.getOrderByOrderNumber(orderNumber);
	}
	
	@Override
	public void readOrdersFromFile() {
		dao.readOrdersFromFile();
	}

	@Override
	public void addOrder(Order order) {
		dao.addOrder(order);
	}

	@Override
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

	@Override
	public void removeOrder(Order order) {
		dao.removeOrder(order);		
	}

}
