package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.enumfiles.Status;
import fpmibsu.outloud.enumfiles.Type;

//TO-DO
public class ReportDao {
    private static Report makeReport(ResultSet resultSet) throws SQLException, DaoException {
        Report report = new Report();
        report.setId(resultSet.getInt("id"));
        report.setCreator(UserDao.findEntityById(resultSet.getInt("creatorid")));
        report.setHelper(UserDao.findEntityById(resultSet.getInt("helperid")));
        report.setStatus(Status.fromString(resultSet.getString("status")));
        report.setText(resultSet.getString("text"));
        report.setTitle(resultSet.getString("title"));
        return report;
    }

    public static List<Report> findAll() throws DaoException {
        List<Report> reports = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM reports;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Report report = makeReport(resultSet);
                reports.add(report);
            }
        } catch(SQLException | DaoException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException ignored) {}
        }
        return reports;
    }

    public static List<Report> findAllByStatus(Status status) throws DaoException {
        List<Report> reports = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            String sqlString = "SELECT * FROM reports WHERE status='" + status.toString() + "';";
            statement = connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Report report = makeReport(resultSet);
                reports.add(report);
            }
        } catch(SQLException | DaoException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException ignored) {}
        }
        return reports;
    }

    public static Report findEntityById(Integer id) throws DaoException{
        Report report = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT * FROM reports WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                report = makeReport(resultSet);
            }
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException ignored) {}
        }
        return report;
    }

    public static boolean isExists(Integer id) throws DaoException {
        int count = 0;
        Report report = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM reports WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt("count");
            }

        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException ignored) {}
        }
        return count > 0;
    }

    public static boolean delete(Integer id) throws DaoException {
        if(!isExists(id)) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "DELETE FROM reports WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
        } catch (SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch (SQLException ignored) {}
        }
        return true;
    }

    public static boolean isExist(Integer id) throws DaoException {
        return findEntityById(id) != null;
    }

    public static boolean create(Report report) throws DaoException {
        if(isExist(report.getId())) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO reports(id, creatorid, helperid, status, text, title) VALUES";
            sqlString += report.toString() + ";";
            statement.executeUpdate(sqlString);
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return true;
    }

    public static Report update(Report report) throws DaoException {
        if(!isExists(report.getId())) {
            create(report);
            return report;
        }
        Report reportToUpdate;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            reportToUpdate = findEntityById(report.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE reports SET ");
            sqlStringBuilder.append("creatorid='").append(report.getCreator().getId()).append("', ");
            sqlStringBuilder.append("helperid='").append(report.getHelper().getId()).append("', ");
            sqlStringBuilder.append("status='").append(report.getStatus().toString()).append("', ");
            sqlStringBuilder.append("text='").append(report.getText()).append("', ");
            sqlStringBuilder.append("title='").append(report.getTitle()).append("'");
            sqlStringBuilder.append(" WHERE id=").append(report.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);
            statement.executeUpdate(sqlString);
        } catch(SQLException exception) {
            throw new DaoException(exception);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException ignored) {}
        }
        return reportToUpdate;
    }
}
