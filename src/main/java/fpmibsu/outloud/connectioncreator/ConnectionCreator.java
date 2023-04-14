package fpmibsu.outloud.connectioncreator;
import java.sql.*;


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

    public static void close(Connection connection) throws SQLException{
        if(connection != null) {
            connection.close();
        }
    }

    public static void close(ResultSet resultSet) throws SQLException {
        if(resultSet != null) {
            resultSet.close();
        }
    }

    public static void close(Statement statement) throws SQLException{
        if(statement != null) {
            statement.close();
        }
    }
}