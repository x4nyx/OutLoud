package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.enumfiles.Status;

public class ReportDao extends AbstractDao{
    public ReportDao() {super();}
    public ReportDao(Connection connection) {
        super(connection);
    }

    private Report makeReport(ResultSet resultSet) throws SQLException, DaoException {
        Report report = new Report();
        UserDao userDao = new UserDao(ConnectionCreator.createConnection());
        report.setId(resultSet.getInt("id"));
        report.setCreator(userDao.findUserById(resultSet.getInt("creatorid")));
        report.setHelper(userDao.findUserById(resultSet.getInt("helperid")));
        report.setStatus(Status.fromString(resultSet.getString("status")));
        report.setText(resultSet.getString("text"));
        report.setTitle(resultSet.getString("title"));
        ConnectionCreator.close(userDao);
        return report;
    }

    public List<Report> findAllReports() throws DaoException {
        List<Report> reports = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM reports;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Report report = makeReport(resultSet);
                reports.add(report);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return reports;
    }

    public List<Report> findAllByStatus(Status status) throws DaoException {
        List<Report> reports = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sqlString = "SELECT * FROM reports WHERE status='" + status.toString() + "';";
            statement = this.connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Report report = makeReport(resultSet);
                reports.add(report);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return reports;
    }

    public Report findReportById(Integer id) throws DaoException{
        Report report = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.prepareStatement("SELECT * FROM reports WHERE id=" + id + ";");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                report = makeReport(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return report;
    }


    public boolean deleteReportById(Integer id) throws DaoException {
        if(!isExists(id)) {
            return false;
        }
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM reports WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean isExists(Integer id) throws DaoException {
        return findReportById(id) != null;
    }

    public boolean createReport(Report report) throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO reports(creatorid, helperid, status, text, title) VALUES";
            sqlString += report + ";";
            statement.executeUpdate(sqlString);
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if(resultSet.next()) {
                report.setId(resultSet.getInt("LAST_INSERT_ID()"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public Report updateReport(Report report) throws DaoException {
        if(!isExists(report.getId())) {
            createReport(report);
            return report;
        }
        Report reportToUpdate;
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
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
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return reportToUpdate;
    }
}
