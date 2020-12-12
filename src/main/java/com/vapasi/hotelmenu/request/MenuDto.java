package com.vapasi.hotelmenu.request;

public class MenuDto {
    private Integer id;
    private String name;
    private double price;

    public MenuDto(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public MenuDto(String name, double price) {
        this.name = name;
        this.price = price;
    }


    public MenuDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
