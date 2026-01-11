package com.company.order;

public record OrderItem (
        String ProductName,
        int quality,
        double price
){

    public double totalPrices(){
        return  quality * price;
    }
}
