package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.AlbumDao;
import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.entitiy.Album;
import fpmibsu.outloud.entitiy.Track;

import java.util.List;

public class AlbumService {
    public Album getAlbumById(Integer albumId) throws DaoException {
        AlbumDao albumDao = new AlbumDao();
        albumDao.createConnection();
        return albumDao.findAlbumById(albumId);
    }

    public boolean addTrack(Integer albumId, Integer trackId) throws DaoException {
        AlbumDao albumDao = new AlbumDao();
        albumDao.createConnection();
        return albumDao.addTrackToAlbum(albumId, trackId);
    }

    public boolean deleteTrackFromAlbum(Integer albumId, Integer trackId) throws DaoException {
        AlbumDao albumDao = new AlbumDao();
        albumDao.createConnection();
        return albumDao.deleteTrackFromAlbum(albumId, trackId);
    }

    public List<Track> getAllTracksFromAlbum(Integer albumId) throws DaoException {
        AlbumDao albumDao = new AlbumDao();
        albumDao.createConnection();
        return albumDao.getTracksFromAlbum(albumId);
    }
}
