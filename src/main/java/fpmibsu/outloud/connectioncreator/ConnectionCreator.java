package fpmibsu.outloud.connectioncreator;
import fpmibsu.outloud.dao.AbstractDao;
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

    public static Connection createConnection(String url, String user, String password) throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException(e);
        }
        return connection;
    }

    public static boolean close(AbstractDao object){
        if(object != null) {
            return object.closeConnection();
        }
        return false;
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
