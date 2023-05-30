package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.dao.ReportDao;
import fpmibsu.outloud.entitiy.Report;
import fpmibsu.outloud.enumfiles.Status;
import fpmibsu.outloud.service.ReportService;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public class ReportServiceImpl extends ServiceImpl implements ReportService {
    public List<Report> getAllReports() throws PersistentException {
        ReportDao reportDao = transaction.createDao(ReportDao.class);
        return reportDao.findAllReports();
    }

    public List<Report> getAllReportsByStatus(Status status) throws PersistentException {
        ReportDao reportDao = transaction.createDao(ReportDao.class);
        return reportDao.findAllByStatus(status);
    }

    public Report getReportById(Integer reportId) throws PersistentException {
        ReportDao reportDao = transaction.createDao(ReportDao.class);
        return reportDao.findReportById(reportId);
    }

    public boolean updateReportStatus(Report report, Status status) throws PersistentException {
        ReportDao reportDao = transaction.createDao(ReportDao.class);
        report.setStatus(status);
        Report updatedReport = reportDao.updateReport(report);
        return updatedReport.getStatus() == status;
    }
}
