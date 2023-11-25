package com.vikovaro.conf.utilities;

import com.sun.tools.javac.Main;
import com.vikovaro.conf.DTO.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.vikovaro.conf.utilities.Load.connection;

public class DatabaseConnection {

    public static void uploadToDataBase(AddRequest addRequest) {
        String spec = "";
        switch(addRequest.getType()) {
            case "processors": spec = "socket";
                break;
            case "motherboards": spec = "socket";
                break;
            case "videocards": spec = "pci";
                break;
            case "powerunits": spec = "wattage";
            default:
                break;
        }
        addComponent(addRequest, spec);

    }

    private static void addComponent(AddRequest addRequest, String spec) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO "+addRequest.getType()+" (name, rang, "+spec+", price) VALUES ('"
                    + addRequest.getName() + "', '" + addRequest.getRang() + "', '" + addRequest.getSpec() + "', '" + addRequest.getPrice() + "')";
            statement.execute(sql);
            statement.close();
        } catch(Exception e) {
            System.out.println("Exception in addComponent: "+e);
        }
    }

    public static ArrayList<Processor> getProcessors() {
        ArrayList<Processor> procList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT name, rang, socket, price FROM processors";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String socket = resultSet.getString("socket");
                int price = resultSet.getInt("price");
                int rang = resultSet.getInt("rang");
                procList.add(new Processor(name,rang,socket,price));
            }
            statement.close();
        } catch(Exception e) {
            System.out.println("Exception in getProcessors: "+e);
        }
        return procList;
    }

    public static ArrayList<Motherboard> getMotherboards() {
        ArrayList<Motherboard> mbList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT name, rang, socket, price FROM motherboards";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String socket = resultSet.getString("socket");
                int price = resultSet.getInt("price");
                int rang = resultSet.getInt("rang");
                mbList.add(new Motherboard(name,rang,socket,price));
            }
            statement.close();
        } catch(Exception e) {
            System.out.println("Exception in getMotherBoards: "+e);
        }
        return mbList;
    }

    public static ArrayList<Videocard> getVideocards() {
        ArrayList<Videocard> vcList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT name, rang, pci, price FROM videocards";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String pci = resultSet.getString("pci");
                int price = resultSet.getInt("price");
                int rang = resultSet.getInt("rang");
                vcList.add(new Videocard(name,rang,pci,price));
            }
            statement.close();
        } catch(Exception e) {
            System.out.println("Exception in getVideocards: "+e);
        }
        return vcList;
    }

    public static ArrayList<Powerunit> getPowerunits() {
        ArrayList<Powerunit> puList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT name, rang, wattage, price FROM powerunits";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String wattage = resultSet.getString("wattage");
                int rang = resultSet.getInt("rang");
                int price = resultSet.getInt("price");
                puList.add(new Powerunit(name,rang,wattage,price));
            }
            statement.close();
        } catch(Exception e) {
            System.out.println("Exception in getPowerunits: "+e);
        }
        return puList;
    }



}
