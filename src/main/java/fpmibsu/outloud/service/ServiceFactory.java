package fpmibsu.outloud.service;

import fpmibsu.outloud.exception.PersistentException;

public interface ServiceFactory {
    <Type extends Service> Type getService(Class<Type> key) throws PersistentException;
    void close();
}
