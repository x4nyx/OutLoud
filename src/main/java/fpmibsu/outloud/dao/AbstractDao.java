package fpmibsu.outloud.dao;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDao {
    protected Connection connection;

    public AbstractDao() {}

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public boolean createConnection() {
        try {
            this.connection = ConnectionCreator.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return this.connection != null;
    }

    public boolean closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
