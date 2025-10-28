package com.example.demoproject.other.designPattern.abstractFactory;

import jakarta.transaction.TransactionManager;

import java.sql.Connection;

// Abstract Factory
interface DatabaseFactory {
    Connection createConnection();
    SqlQueryBuilder createQueryBuilder();
    TransactionManager createTransactionManager();
}

// Oracle implementatsiya
class OracleDatabaseFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() {
        return new OracleConnection();
    }

    @Override
    public SqlQueryBuilder createQueryBuilder() {
        return new OracleSqlBuilder();
    }

    @Override
    public TransactionManager createTransactionManager() {
        return new OracleTransactionManager();
    }
}

// MySQL implementatsiya
class MySQLDatabaseFactory implements DatabaseFactory {
    @Override
    public Connection createConnection() {
        return new MySQLConnection();
    }

    @Override
    public SqlQueryBuilder createQueryBuilder() {
        return new MySQLQueryBuilder();
    }

    @Override
    public TransactionManager createTransactionManager() {
        return new MySQLTransactionManager();
    }
}