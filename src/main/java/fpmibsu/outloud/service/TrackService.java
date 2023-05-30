package fpmibsu.outloud.service;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Track;

import java.util.List;

public interface TrackService extends Service {

    List<Track> getAllTracks() throws PersistentException;

    Track getTrackById(Integer trackId) throws PersistentException;

    List<Track> getTracks(String name) throws PersistentException;
}
