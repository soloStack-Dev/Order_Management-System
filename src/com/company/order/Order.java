package com.company.order;

import java.util.List;

public record Order (
    long orderId,
    String CustomerName,
    List<OrderItem> items,
    OrderStatus status
){
    public double totalAmount(){
        return items.stream()
                .mapToDouble(OrderItem::totalPrices)
                .sum();
    }

    public Order make(){
        return new Order(orderId,CustomerName,items,status);
    }
}
