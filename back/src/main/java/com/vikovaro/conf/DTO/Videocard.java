package com.vikovaro.conf.DTO;

public class Videocard {
    String name;
    int rang;
    String pci;
    int price;

    public Videocard(String name, int rang, String pci, int price) {
        this.name = name;
        this.rang = rang;
        this.pci = pci;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPci() {
        return pci;
    }

    public int getPrice() {
        return price;
    }
    public int getRang() {
        return rang;
    }
}
