package com.example.demoproject.other.designPattern.singleton;

import lombok.Data;

import java.util.UUID;

@Data
public class Database {


    private UUID pkey = UUID.randomUUID();


    private static final class DatabaseHolder {
        private static final Database database = new Database();
    }

    public static Database getInstance() {
        return DatabaseHolder.database;
    }

}
