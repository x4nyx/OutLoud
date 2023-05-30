package fpmibsu.outloud.dao;

import fpmibsu.outloud.exception.PersistentException;

public interface Transaction {
    <Type extends BaseDao<?>> Type createDao(Class<Type> key) throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
