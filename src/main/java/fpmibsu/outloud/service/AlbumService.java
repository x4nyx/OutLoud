package fpmibsu.outloud.service;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Album;
import fpmibsu.outloud.entitiy.Track;

import java.util.List;

public interface AlbumService extends Service {

    Album getAlbumById(Integer albumId) throws PersistentException;

    boolean addTrack(Integer albumId, Integer trackId) throws PersistentException;

    boolean deleteTrackFromAlbum(Integer albumId, Integer trackId) throws PersistentException;

    List<Track> getAllTracksFromAlbum(Integer albumId) throws PersistentException;
}
