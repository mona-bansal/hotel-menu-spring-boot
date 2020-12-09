package com.vapasi.hotelmenu.request;

public class MenuDto {

    private String name;
    private double price;

    public MenuDto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public MenuDto() {
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
