package fpmibsu.outloud.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import fpmibsu.outloud.connectioncreator.ConnectionCreator;
import fpmibsu.outloud.entitiy.*;


public class GenreDao extends AbstractDao{
    public GenreDao() {super();}
    public GenreDao(Connection connection) {
        super(connection);
    }

    private static Genre makeGenre(ResultSet resultSet) throws DaoException {
        Genre genre;
        try {
            genre = new Genre();
            genre.setId(resultSet.getInt("id"));
            genre.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return genre;
    }

    public List<Genre> findAllGenres() throws DaoException {
        List<Genre> genres = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = this.connection.prepareStatement("SELECT * FROM genres;");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                genres.add(makeGenre(resultSet));
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return genres;
    }

    public Genre findGenreById(Integer id) throws DaoException {
        Genre genre = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            String sqlString = "SELECT * FROM genres WHERE id=" + id + ";";
            statement = this.connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                genre = makeGenre(resultSet);
            }
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(resultSet);
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return genre;
    }

    public boolean deleteGenreById(Integer id) throws DaoException {
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM genres WHERE id=" + id + ";";
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean createGenre(Genre entity) throws DaoException {
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "INSERT INTO genres(name) VALUES";
            sqlString += entity.toString() + ";";            
            statement.executeUpdate(sqlString);
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            if(resultSet.next()) {
                entity.setId(resultSet.getInt("LAST_INSERT_ID()"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionCreator.close(statement);
                ConnectionCreator.close(resultSet);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    public Genre updateGenre(Genre entity) throws DaoException {
        Genre genre;
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            genre = findGenreById(entity.getId());
            StringBuilder sqlStringBuilder = new StringBuilder("UPDATE genres SET ");
            sqlStringBuilder.append("name='").append(entity.getName()).append("' ");
            sqlStringBuilder.append("WHERE id=").append(entity.getId()).append(";");
            String sqlString = new String(sqlStringBuilder);           
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                ConnectionCreator.close(statement);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return genre;
    }
}
