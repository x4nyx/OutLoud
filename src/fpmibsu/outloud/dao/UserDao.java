package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import fpmibsu.outloud.entitiy.*;

public class UserDao extends AbstractController<User, Integer>{

    @Override
    public List<User> findAll() throws DaoException {
        // TODO Auto-generated method stub
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            
        } catch(/*SQL*/Exception exception) {
            throw new DaoException(exception);
        } finally {

        }
        return users;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findEntityById'");
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean create(User entity) throws DaoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    
    @Override
    public User update(User entity) throws DaoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
