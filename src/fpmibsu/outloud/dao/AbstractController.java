package fpmibsu.outloud.dao;
import java.util.List;


public abstract class AbstractController <E, K>{
    public abstract List<E> findAll() throws DaoException;
    public abstract E findEntityById(K id) throws DaoException;
    public abstract boolean delete(K id) throws DaoException;
    public abstract boolean create(E entity) throws DaoException;
    public abstract E update(E entity) throws DaoException;

}
