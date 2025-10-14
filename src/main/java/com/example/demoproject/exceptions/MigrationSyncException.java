package com.example.demoproject.exceptions;

import lombok.Getter;

@Getter
public class MigrationSyncException extends RuntimeException {

    public MigrationSyncException(Exception e) {
        super(e.getMessage());
    }

    public MigrationSyncException(String message, String developerMessage) {
        super(message);
    }
}