package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.TrackDao;
import fpmibsu.outloud.entitiy.Track;

import java.util.List;

public class TrackService {
    TrackDao trackDao = new TrackDao();

    public TrackService() {
        trackDao.createConnection();
    }

    public List<Track> getAllTracks() throws DaoException {
        return trackDao.findAllTracks();
    }

    public Track getTrackById(Integer trackId) throws DaoException {
        return trackDao.findTrackById(trackId);
    }
}
