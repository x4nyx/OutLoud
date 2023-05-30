package fpmibsu.outloud.dao;

import fpmibsu.outloud.entitiy.Genre;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public interface GenreDao extends BaseDao<Genre>{
    List<Genre> findAllGenres() throws PersistentException;
    Genre findGenreById(Integer id) throws PersistentException;
    boolean deleteGenreById(Integer id) throws PersistentException;
    boolean createGenre(Genre entity) throws PersistentException;
    Genre updateGenre(Genre entity) throws PersistentException;

}
