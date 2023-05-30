package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.GenreDao;
import fpmibsu.outloud.entitiy.Genre;
import fpmibsu.outloud.service.GenreService;

import java.util.List;

public class GenreServiceImpl extends ServiceImpl implements GenreService {

    public List<Genre> getAllGenres() throws PersistentException {
        GenreDao genreDao = transaction.createDao(GenreDao.class);
        return genreDao.findAllGenres();
    }

    public Genre getGenreById(Integer genreId) throws PersistentException {
        GenreDao genreDao = transaction.createDao(GenreDao.class);
        return genreDao.findGenreById(genreId);
    }
}
