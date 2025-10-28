package com.example.demoproject.other.designPattern.factory;

public class DatabaseFactory {

    public static Database getInstance(String dbType) {

        if (dbType.equalsIgnoreCase("postgres")) {
            return new PostgresDB();
        } else if (dbType.equalsIgnoreCase("mongodb")) {
            return new MongoDB();
        } else if (dbType.equalsIgnoreCase("oracle")) {
            return new OracleDB();
        }

        throw new IllegalArgumentException("Unknown database type: " + dbType);
    }

}
