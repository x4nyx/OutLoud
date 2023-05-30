package fpmibsu.outloud.service;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Report;
import fpmibsu.outloud.enumfiles.Status;

import java.util.List;

public interface ReportService extends Service{

    List<Report> getAllReports() throws PersistentException;

    List<Report> getAllReportsByStatus(Status status) throws PersistentException;

    Report getReportById(Integer reportId) throws PersistentException;

    boolean updateReportStatus(Report report, Status status) throws PersistentException;
}
