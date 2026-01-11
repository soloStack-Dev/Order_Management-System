package com.company.order;

public class PaymentService {
    void processPayment(double amount){
        if(amount <= 0){
            throw new Exception("Invalid payment amount");
        }

        if(amount > 100_000){
            throw new Exception("Payment limit exit");
        }

        IO.println("Payment successful: ₹" + amount);
    }
}
