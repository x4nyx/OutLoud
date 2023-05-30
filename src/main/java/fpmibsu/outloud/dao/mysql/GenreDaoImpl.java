package fpmibsu.outloud.dao.mysql;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.GenreDao;
import fpmibsu.outloud.entitiy.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl extends BaseDaoImpl implements GenreDao {
    private static Genre makeGenre(ResultSet resultSet) throws PersistentException {
        Genre genre;
        try {
            genre = new Genre();
            genre.setId(resultSet.getInt("id"));
            genre.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
        return genre;
    }

    public List<Genre> findAllGenres() throws PersistentException {
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
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return genres;
    }

    public Genre findGenreById(Integer id) throws PersistentException {
        Genre genre = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            String sqlString = "SELECT * FROM genres WHERE id='" + id + "';";
            statement = this.connection.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                genre = makeGenre(resultSet);
            }
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return genre;
    }

    public boolean deleteGenreById(Integer id) throws PersistentException {
        Statement statement = null;
        try{
            statement = this.connection.createStatement();
            String sqlString = "DELETE FROM genres WHERE id='" + id + "';";
            statement.executeUpdate(sqlString);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean createGenre(Genre entity) throws PersistentException {
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
            //   else{
            //       logger.error("There is no autoincremented index after trying to add record into table `genres`");
            //   }
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public Genre updateGenre(Genre entity) throws PersistentException {
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
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return genre;
    }
}
