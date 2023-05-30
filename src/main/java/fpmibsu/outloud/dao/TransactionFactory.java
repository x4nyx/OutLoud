package fpmibsu.outloud.dao;

import fpmibsu.outloud.exception.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;
    void close();
}
