package com.example.orchidservice.service.imp;

import com.example.orchidservice.pojo.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Integer id);
    Order saveOrder(Order order);
    void deleteOrder(Integer id);
    List<Order> getOrdersByAccount(Integer accountId);
    List<Order> getOrdersByStatus(String orderStatus);
    List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate);
}
