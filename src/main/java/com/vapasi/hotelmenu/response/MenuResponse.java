package com.vapasi.hotelmenu.response;

public class MenuResponse {
    private Integer id;
    private String name;
    private double price;

    public MenuResponse(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public MenuResponse() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
