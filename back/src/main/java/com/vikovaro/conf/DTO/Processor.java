package com.vikovaro.conf.DTO;

public class Processor {
    String name;
    String socket;
    int rang;
    int price;

    public Processor(String name, int rang, String socket, int price) {
        this.name = name;
        this.rang = rang;
        this.socket = socket;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSocket() {
        return socket;
    }

    public int getPrice() {
        return price;
    }

    public int getRang() {
        return rang;
    }
}
