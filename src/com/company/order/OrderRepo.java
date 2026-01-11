package com.company.order;

import java.util.*;

public class OrderRepo {
    private final Map<Long, Order> database = new HashMap<>();

    void save(Order order){
        database.put(order.orderId(),order);
    }

    public Optional<Order> findById(long orderId) {
        return Optional.ofNullable(database.get(orderId));
    }
}
