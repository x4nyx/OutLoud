package fpmibsu.outloud.dao.mysql;

import fpmibsu.outloud.dao.*;
import fpmibsu.outloud.exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionImpl implements Transaction {

    private static Map<Class<? extends BaseDao<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();
    static {
        classes.put(AlbumDao.class, AlbumDaoImpl.class);
        classes.put(GenreDao.class, GenreDaoImpl.class);
        classes.put(GroupDao.class, GroupDaoImpl.class);
        classes.put(PostDao.class, PostDaoImpl.class);
        classes.put(ReportDao.class, ReportDaoImpl.class);
        classes.put(TrackDao.class, TrackDaoImpl.class);
        classes.put(UserDao.class, UserDaoImpl.class);
    }

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Type extends BaseDao<?>> Type createDao(Class<Type> key) throws PersistentException {
        Class<? extends BaseDaoImpl> value = classes.get(key);
        if(value != null) {
            try {
                BaseDaoImpl dao = value.newInstance();
                dao.setConnection(connection);
                return (Type)dao;
            } catch(InstantiationException | IllegalAccessException e) {
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch(SQLException e) {
            throw new PersistentException(e);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            throw new PersistentException(e);
        }
    }
}
