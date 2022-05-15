package com.example.myapplicationmad;

public class FoodSupplyDetails {
    String dishes,quantity,price,description;

    public FoodSupplyDetails(){

    }

    public FoodSupplyDetails(String dishes, String quantity, String price, String description) {
        this.dishes = dishes;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


