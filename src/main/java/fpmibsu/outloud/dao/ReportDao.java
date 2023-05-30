package fpmibsu.outloud.dao;

import fpmibsu.outloud.entitiy.Report;
import fpmibsu.outloud.enumfiles.Status;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public interface ReportDao extends BaseDao<Report> {
    List<Report> findAllReports() throws PersistentException;
    List<Report> findAllByStatus(Status status) throws PersistentException;
    Report findReportById(Integer id) throws PersistentException;
    boolean deleteReportById(Integer id) throws PersistentException;
    boolean isExists(Integer id) throws PersistentException;
    boolean createReport(Report report) throws PersistentException;
    Report updateReport(Report report) throws PersistentException;

}
