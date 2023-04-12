package fpmibsu.outloud.connectioncreator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionCreator{
    public static final String url = "jdbc:mysql://localhost:3306/OutLoud";
    public static final String userName = "LINUX228";
    public static final String password = "123456";
    public static Connection createConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException(e);
        }
        return connection;
    }
}
