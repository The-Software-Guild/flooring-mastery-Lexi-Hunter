package com.wileyedge.FlooringMastery.dao;

import java.time.LocalDate;
import java.util.List;
import com.wileyedge.FlooringMastery.model.Order;

public interface OrderDao {
    void addOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByDate(LocalDate date);
    Order getOrderByOrderNumber(int orderNumber);
    void readOrdersFromFile();
    void removeOrder(Order order);
}
