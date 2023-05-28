package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.TrackDao;
import fpmibsu.outloud.entitiy.Track;

import java.util.List;

public class TrackService {
    public List<Track> getAllTracks() throws DaoException {
        TrackDao trackDao = new TrackDao();
        trackDao.createConnection();
        return trackDao.findAllTracks();
    }

    public Track getTrackById(Integer trackId) throws DaoException {
        TrackDao trackDao = new TrackDao();
        trackDao.createConnection();
        return trackDao.findTrackById(trackId);
    }
}
