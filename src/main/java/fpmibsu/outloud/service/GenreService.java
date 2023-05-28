package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.GenreDao;
import fpmibsu.outloud.entitiy.Genre;

import java.util.List;

public class GenreService {
    GenreDao genreDao = new GenreDao();

    public GenreService() {
        genreDao.createConnection();
    }

    public List<Genre> getAllGenres() throws DaoException {
        return genreDao.findAllGenres();
    }

    public Genre getGenreById(Integer genreId) throws DaoException {
        return genreDao.findGenreById(genreId);
    }
}
