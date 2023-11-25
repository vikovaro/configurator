package com.vikovaro.conf.utilities;
import org.sqlite.JDBC;
import org.xml.sax.ErrorHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Load {
    public static Connection connection;
    public static void startup() {
        JDBC();
        getProcessorsTable();
        getVideoTable();
        getPowerUnitsTable();
        getMotherBoardsTable();
    }

    private static void getProcessorsTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS processors (" +
                    "name VARCHAR(50) NOT NULL," +
                    "rang DECIMAL NOT NULL," +
                    "socket DECIMAL NOT NULL," +
                    "price DECIMAL NOT NULL)";

            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println("Exception in getProcessorsTable: "+e);
        }
    }
    private static void getVideoTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS videocards (" +
                    "name VARCHAR(50) NOT NULL," +
                    "rang DECIMAL NOT NULL," +
                    "pci DECIMAL NOT NULL," +
                    "price DECIMAL NOT NULL)";

            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println("Exception in getVideoTable: "+e);
        }
    }
    private static void getPowerUnitsTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS powerunits (" +
                    "name VARCHAR(50) NOT NULL," +
                    "rang DECIMAL NOT NULL," +
                    "wattage DECIMAL NOT NULL," +
                    "price DECIMAL NOT NULL)";
            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println("Exception in getPowerUnitsTable: "+e);
        }
    }
    private static void getMotherBoardsTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS motherboards (" +
                    "name VARCHAR(50) NOT NULL," +
                    "rang DECIMAL NOT NULL," +
                    "socket DECIMAL NOT NULL," +
                    "price DECIMAL NOT NULL)";
            statement.execute(sql);
            statement.close();
        } catch (Exception e) {
            System.out.println("Exception in getMotherBoardsTable: "+e);
        }
    }

    private static void JDBC() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Exception in JDBC: "+e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Conf.db");
        } catch (Exception e) {
            System.out.println("Exception in trying connection: "+e);
        }
    }
}
