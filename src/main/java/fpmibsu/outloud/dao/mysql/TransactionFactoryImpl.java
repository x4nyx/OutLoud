package fpmibsu.outloud.dao.mysql;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.Transaction;
import fpmibsu.outloud.dao.TransactionFactory;
import fpmibsu.outloud.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    public TransactionFactoryImpl() throws PersistentException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch(SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws PersistentException {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {}
    }
}