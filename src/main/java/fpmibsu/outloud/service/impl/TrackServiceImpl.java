package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.dao.TrackDao;
import fpmibsu.outloud.entitiy.Track;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.service.TrackService;

import java.util.List;

public class TrackServiceImpl extends ServiceImpl implements TrackService {
    public List<Track> getAllTracks() throws PersistentException {
        TrackDao trackDao = transaction.createDao(TrackDao.class);
        return trackDao.findAllTracks();
    }

    public Track getTrackById(Integer trackId) throws PersistentException {
        TrackDao trackDao = transaction.createDao(TrackDao.class);
        return trackDao.findTrackById(trackId);
    }

    public List<Track> getTracks(String name) throws PersistentException {
        TrackDao trackDao = transaction.createDao(TrackDao.class);
        return trackDao.findTracksByName(name);
    }
}
