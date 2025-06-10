package com.example.orchidservice.repository;


import com.example.orchidservice.pojo.Order;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository {
    List<Order> findAll();
    Optional<Order> findById(Integer id);
    Order save(Order order);
    void deleteById(Integer id);
    List<Order> findByAccountId(Integer accountId);
    List<Order> findByOrderStatus(String orderStatus);
    List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}
