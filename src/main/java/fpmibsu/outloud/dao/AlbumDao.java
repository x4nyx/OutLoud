package fpmibsu.outloud.dao;

import fpmibsu.outloud.entitiy.Album;
import fpmibsu.outloud.entitiy.Track;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public interface AlbumDao extends BaseDao<Album> {
    List<Track> findAllTracksInAlbum(Integer albumid) throws PersistentException;
    boolean addTrackToAlbum(Integer albumid, Integer trackid);
    boolean deleteTrackFromAlbum(Integer albumid, Integer trackid);
    boolean isAlbumTrackListEmpty(Integer albumid) throws PersistentException;
    List<Track> getTracksFromAlbum(Integer albumid) throws PersistentException;
    boolean isExist(Integer albumid) throws PersistentException;
    Album findAlbumById(Integer id) throws PersistentException;
    List<Album> findAlbumByName(String nameSubstr) throws PersistentException;
    boolean deleteAlbumById(Integer id) throws PersistentException;
    Album updateAlbum(Album album) throws PersistentException;
    boolean deleteAllTracksFromAlbum(Integer albumid) throws PersistentException;
    boolean updateTrackList(Integer albumid, List<Track> tracks) throws PersistentException;
    boolean createAlbum(Album album) throws PersistentException;
    public List<Album> findAllAlbums() throws PersistentException;
}
