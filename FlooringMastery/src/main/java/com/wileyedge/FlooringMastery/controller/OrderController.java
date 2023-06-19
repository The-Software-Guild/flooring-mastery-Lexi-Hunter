package com.wileyedge.FlooringMastery.controller;

public interface OrderController {

    void run();
    void displayOrders();
    void addOrder();
    void editOrder();
    void removeOrder();
    void exportAllData();
    void unknownCommand();
    void exitMessage();
}
