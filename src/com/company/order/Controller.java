package com.company.order;

import java.util.List;

public class Controller {
    static void main(String[] args){
        var orderRepository = new OrderRepo();
        var paymentServices = new PaymentService();
        var orderService = new OrderService(orderRepository,paymentServices);

        var items = List.of(
                new OrderItem("Laptop", 1, 75_000),
                new OrderItem("Mouse", 2, 1_500)
        );

        var order = new Order(
                1L,
                "Faleel",
                items,
                OrderStatus.CREATED
        );

        orderService.placeOrder(order);
        orderService.payForOrder(1L);
    }
}
