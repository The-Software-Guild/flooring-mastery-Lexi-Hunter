package com.wileyedge.FlooringMastery.service;

import java.time.LocalDate;
import java.util.List;
import com.wileyedge.FlooringMastery.model.Order;

public interface OrderService {
    List<Order> getOrdersByDate(LocalDate date);
    List<Order> getAllOrders();
    Order getOrderByOrderNumber(int orderNumber);
    void readOrdersFromFile();
    void addOrder(Order order);
    void updateOrder(Order order, Order changedOrder);
    void removeOrder(Order order);
}
