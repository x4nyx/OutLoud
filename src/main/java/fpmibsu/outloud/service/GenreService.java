package fpmibsu.outloud.service;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Genre;

import java.util.List;

public interface GenreService extends Service {

    List<Genre> getAllGenres() throws PersistentException;

    Genre getGenreById(Integer genreId) throws PersistentException;
}
