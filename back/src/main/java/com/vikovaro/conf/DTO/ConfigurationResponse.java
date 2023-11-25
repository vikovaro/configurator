package com.vikovaro.conf.DTO;

public class ConfigurationResponse {
    private String processor;
    private String videocard;
    private String motherboard;
    private String RAM; // Random Access Memory ОЗУ
    private String ROM; //  read-only memory ПЗУ
    private String powerunit;
    private int price;

    public ConfigurationResponse(String processor, String videocard, String motherboard, String RAM, String ROM, String powerunit, int price) {
        this.processor = processor;
        this.videocard = videocard;
        this.motherboard = motherboard;
        this.RAM = RAM;
        this.ROM = ROM;
        this.powerunit = powerunit;
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getVideocard() {
        return videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getROM() {
        return ROM;
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public String getPowerunit() {
        return powerunit;
    }

    public void setPowerunit(String powerunit) {
        this.powerunit = powerunit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
