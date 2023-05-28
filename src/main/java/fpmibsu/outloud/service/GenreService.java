package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.GenreDao;
import fpmibsu.outloud.entitiy.Genre;

import java.util.List;

public class GenreService {
    public List<Genre> getAllGenres() throws DaoException {
        GenreDao genreDao = new GenreDao();
        genreDao.createConnection();
        return genreDao.findAllGenres();
    }

    public Genre getGenreById(Integer genreId) throws DaoException {
        GenreDao genreDao = new GenreDao();
        genreDao.createConnection();
        return genreDao.findGenreById(genreId);
    }
}
