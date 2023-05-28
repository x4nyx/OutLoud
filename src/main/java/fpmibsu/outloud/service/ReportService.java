package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.ReportDao;
import fpmibsu.outloud.entitiy.Report;
import fpmibsu.outloud.enumfiles.Status;

import java.util.List;

public class ReportService {
    ReportDao reportDao = new ReportDao();

    public ReportService() {
        reportDao.createConnection();
    }

    public List<Report> getAllReports() throws DaoException {
        return reportDao.findAllReports();
    }

    public List<Report> getAllReportsByStatus(Status status) throws DaoException {
        return reportDao.findAllByStatus(status);
    }

    public Report getReportById(Integer reportId) throws DaoException {
        return reportDao.findReportById(reportId);
    }

    public boolean updateReportStatus(Report report, Status status) throws DaoException {
        report.setStatus(status);
        Report updatedReport = reportDao.updateReport(report);
        return updatedReport.getStatus() == status;
    }
}
