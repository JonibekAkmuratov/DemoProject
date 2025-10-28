package com.example.demoproject.other.designPattern.factory;

public class Main {

    public static void main(String[] args) {
        Database postgresDB = DatabaseFactory.getInstance("postgres");
        Database oracleDB = DatabaseFactory.getInstance("oracle");


    }
}
