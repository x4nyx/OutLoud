package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.ReportDao;
import fpmibsu.outloud.entitiy.Report;
import fpmibsu.outloud.enumfiles.Status;

import java.util.List;

public class ReportService {
    public List<Report> getAllReports() throws DaoException {
        ReportDao reportDao = new ReportDao();
        reportDao.createConnection();
        return reportDao.findAllReports();
    }

    public List<Report> getAllReportsByStatus(Status status) throws DaoException {
        ReportDao reportDao = new ReportDao();
        reportDao.createConnection();
        return reportDao.findAllByStatus(status);
    }

    public Report getReportById(Integer reportId) throws DaoException {
        ReportDao reportDao = new ReportDao();
        reportDao.createConnection();
        return reportDao.findReportById(reportId);
    }

    public boolean updateReportStatus(Report report, Status status) throws DaoException {
        report.setStatus(status);
        ReportDao reportDao = new ReportDao();
        reportDao.createConnection();
        Report updatedReport = reportDao.updateReport(report);
        return updatedReport.getStatus() == status;
    }
}
