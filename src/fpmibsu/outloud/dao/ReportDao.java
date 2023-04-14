package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.enumfiles.Status;

//TO-DO
public class ReportDao {
    private static Report makeReport(ResultSet resultSet) throws SQLException, DaoException {
        Report report = new Report();
        report.setId(resultSet.getInt("id"));
        report.setCreator(UserDao.findUserById(resultSet.getInt("creatorid")));
        report.setHelper(UserDao.findUserById(resultSet.getInt("helperid")));
        report.setStatus(Status.fromString(resultSet.getString("status")));
        report.setText(resultSet.getString("text"));
        report.setTitle(resultSet.getString("title"));
        return report;
    }

    public static List<Report> findAllReports() throws DaoException {
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
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
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
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return reports;
    }

    public static Report findReportById(Integer id) throws DaoException{
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
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return report;
    }


    public static boolean deleteReportById(Integer id) throws DaoException {
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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean isExists(Integer id) throws DaoException {
        return findReportById(id) != null;
    }

    public static boolean createReport(Report report) throws DaoException {
        if(isExists(report.getId())) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            String sqlString = "INSERT INTO reports(id, creatorid, helperid, status, text, title) VALUES";
            sqlString += report + ";";
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static Report updateReport(Report report) throws DaoException {
        if(!isExists(report.getId())) {
            createReport(report);
            return report;
        }
        Report reportToUpdate;
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            reportToUpdate = findReportById(report.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE reports SET ");
            sqlStringBuilder.append("creatorid='").append(report.getCreator().getId()).append("', ");
            sqlStringBuilder.append("helperid='").append(report.getHelper().getId()).append("', ");
            sqlStringBuilder.append("status='").append(report.getStatus().toString()).append("', ");
            sqlStringBuilder.append("text='").append(report.getText()).append("', ");
            sqlStringBuilder.append("title='").append(report.getTitle()).append("'");
            sqlStringBuilder.append(" WHERE id=").append(report.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(connection);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return reportToUpdate;
    }
}
