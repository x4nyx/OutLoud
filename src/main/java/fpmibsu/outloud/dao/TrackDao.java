package fpmibsu.outloud.dao;

import fpmibsu.outloud.entitiy.Track;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public interface TrackDao extends BaseDao<Track>{
    List<Track> findAllTracks() throws PersistentException;
    Track findTrackById(Integer id) throws PersistentException;
    List<Track> findTracksByName(String nameSubstr) throws PersistentException;
    boolean isExists(Integer id) throws PersistentException;
    boolean createTrack(Track track) throws PersistentException;
    Track updateTrack(Track track) throws PersistentException;
    boolean deleteTrackById(Integer id) throws PersistentException;

}
