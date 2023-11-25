package com.vikovaro.conf.DTO;

public class Powerunit {
    String name;
    String wattage;
    int rang;
    int price;

    public Powerunit(String name, int rang, String wattage, int price) {
        this.name = name;
        this.wattage = wattage;
        this.price = price;
        this.rang = rang;
    }

    public String getName() {
        return name;
    }

    public String getWattage() {
        return wattage;
    }

    public int getPrice() {
        return price;
    }

    public int getRang() {
        return rang;
    }
}
