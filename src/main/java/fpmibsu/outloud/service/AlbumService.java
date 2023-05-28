package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.AlbumDao;
import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.TrackDao;
import fpmibsu.outloud.entitiy.Album;
import fpmibsu.outloud.entitiy.Track;

import java.util.List;

public class AlbumService {
    private AlbumDao albumDao = new AlbumDao();

    public AlbumService() {
        albumDao.createConnection();
    }

    public Album getAlbumById(Integer albumId) throws DaoException {
        return this.albumDao.findAlbumById(albumId);
    }

    public boolean addTrack(Integer albumId, Integer trackId) throws DaoException {
        return this.albumDao.addTrackToAlbum(albumId, trackId);
    }

    public boolean deleteTrackFromAlbum(Integer albumId, Integer trackId) throws DaoException {
        return this.albumDao.deleteTrackFromAlbum(albumId, trackId);
    }

    public List<Track> getAllTracksFromAlbum(Integer albumId) throws DaoException {
        return this.albumDao.getTracksFromAlbum(albumId);
    }
}
