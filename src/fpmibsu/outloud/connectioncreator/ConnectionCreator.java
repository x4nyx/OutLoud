package fpmibsu.outloud.connectioncreator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionCreator{
    public static final String url = "";
    public static final String userName = "";
    public static final String password = "";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
}
