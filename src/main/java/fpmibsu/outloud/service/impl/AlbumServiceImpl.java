package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.dao.AlbumDao;
import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Album;
import fpmibsu.outloud.entitiy.Track;
import fpmibsu.outloud.service.AlbumService;

import java.util.List;

public class AlbumServiceImpl extends ServiceImpl implements AlbumService {
    public Album getAlbumById(Integer albumId) throws PersistentException {
        AlbumDao albumDao = transaction.createDao(AlbumDao.class);
        return albumDao.findAlbumById(albumId);
    }

    public boolean addTrack(Integer albumId, Integer trackId) throws PersistentException {
        AlbumDao albumDao = transaction.createDao(AlbumDao.class);
        return albumDao.addTrackToAlbum(albumId, trackId);
    }

    public boolean deleteTrackFromAlbum(Integer albumId, Integer trackId) throws PersistentException {
        AlbumDao albumDao = transaction.createDao(AlbumDao.class);
        return albumDao.deleteTrackFromAlbum(albumId, trackId);
    }

    public List<Track> getAllTracksFromAlbum(Integer albumId) throws PersistentException {
        AlbumDao albumDao = transaction.createDao(AlbumDao.class);
        return albumDao.getTracksFromAlbum(albumId);
    }
}
