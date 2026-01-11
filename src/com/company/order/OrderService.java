package com.company.order;

public class OrderService {
    private final OrderRepo orderrepo;
    private final PaymentService paymentservice;

     OrderService(OrderRepo orderrepo,PaymentService paymentservice){
        this.orderrepo = orderrepo;
        this.paymentservice = paymentservice;
    }

    void placeOrder(Order order){
         orderrepo.save(order);
         IO.println("order placed: "+order.orderId());
    }

    void payForOrder(long orderId){
         var order = orderrepo.findById(orderId)
                 .orElseThrow(() -> new IllegalArgumentException("Order not founded!"));

         paymentservice.processPayment(order.totalAmount());

         var paidOrder = order.make();
         orderrepo.save(paidOrder);

         IO.println("Order marked as PAID");
    }

}
