package fpmibsu.outloud.dao.mysql;

import java.sql.Connection;

abstract public class BaseDaoImpl {
    protected Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
