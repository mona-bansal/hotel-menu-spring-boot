package com.vapasi.hotelmenu.response;

import java.util.Objects;

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

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuResponse that = (MenuResponse) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
