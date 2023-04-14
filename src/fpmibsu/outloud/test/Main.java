package fpmibsu.outloud.test;

import fpmibsu.outloud.dao.*;
import fpmibsu.outloud.entitiy.*;
import fpmibsu.outloud.dao.DaoException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void userTest() throws DaoException {
        System.out.println("1. USER_TEST:");
        System.out.println("*test 1. output:");
        List<User> users = UserDao.findAll();;
        for(User us : users) {
            System.out.println(us.toString());
        }
        System.out.println("\n*test 2. findbyid:");
        User user2 = UserDao.findEntityById(3);
        System.out.println(user2.toString());

        System.out.println("\n*test 3. create:");
        User userToCreate = new User(user2);
        userToCreate.setId(4);
        userToCreate.setName("CREATED");
        UserDao.create(userToCreate);
        users = UserDao.findAll();;
        for(User us : users) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. delete:");
        UserDao.delete(4);
        users = UserDao.findAll();
        for(User us : users) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. update:");
        User userUpdater = new User(user2);
        userUpdater.setName("UPDATED KID");
        UserDao.update(userUpdater);
        users = UserDao.findAll();
        for(User us : users) {
            System.out.println(us.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void albumTest() throws DaoException {
        System.out.println("2. ALBUM_TEST:");
        System.out.println("*test 1. output:");
        List<Album> albums = AlbumDao.findAll();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 2. find_by_id:");
        Album album2 = AlbumDao.findEntityById(3);
        System.out.println(album2);

        System.out.println("\n*test 3. find_by_name:");
        List<Album> album3 = AlbumDao.findAlbumByName("date");
        for(Album us : album3) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. create:");
        Album album4 = new Album(album2);
        album4.setId(4);
        album4.setName("CREATE");
        AlbumDao.create(album4);
        albums = AlbumDao.findAll();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        AlbumDao.delete(album4.getId());
        albums = AlbumDao.findAll();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 6. update:");
        Album album6 = new Album(album2);
        album6.setName("UPDATED");
        AlbumDao.update(album6);
        albums = AlbumDao.findAll();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 7. update_tracks:");
        List<Track> tracks = new ArrayList<>();
        tracks.add(TrackDao.findEntityById(1));
        tracks.add(TrackDao.findEntityById(2));
        tracks.add(TrackDao.findEntityById(3));
        AlbumDao.updateTrackList(2, tracks);
        tracks = AlbumDao.getTracks(2);
        if(tracks != null) {
            for (Track track : tracks) {
                System.out.println(track);
            }
        }

        System.out.println("\n*test 8. add_track:");
        AlbumDao.addTrack(2, 1);
        tracks = AlbumDao.getTracks(2);
        if(tracks != null) {
            for (Track track : tracks) {
                System.out.println(track);
            }
        }

        System.out.println("\n*test 9. delete_track:");
        AlbumDao.deleteTrackFromAlbum(2, 1);
        tracks = AlbumDao.getTracks(2);
        if(tracks != null) {
            for (Track track : tracks) {
                System.out.println(track);
            }
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void genreTest() throws DaoException {
        System.out.println("3. GENRE_TEST:");
        System.out.println("*test 1. findAll:");
        List<Genre> genres = GenreDao.findAll();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }
        System.out.println("\n*test 2. find_by_id:");
        Genre genre2 = GenreDao.findEntityById(2);
        System.out.println(genre2);

        System.out.println("\n*test 3. create:");
        Genre genre3 = new Genre(genre2);
        genre3.setId(4);
        genre3.setName("phonk");
        GenreDao.create(genre3);
        genres = GenreDao.findAll();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. update:");
        Genre genre4 = new Genre(genre3);
        genre4.setName("UPDATED");
        GenreDao.update(genre4);
        genres = GenreDao.findAll();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        GenreDao.delete(4);
        genres = GenreDao.findAll();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }

        System.out.println("-------------------------------------------------------------\n");
    }

    private static void groupTest() throws DaoException {
        System.out.println("4. GROUP_TEST:");
        System.out.println("*test 1. findAll:");
        List<Group> groups = GroupDao.findAll();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 2. find_by_id:");
        Group group2 = GroupDao.findEntityById(3);
        System.out.println(group2);

        System.out.println("\n*test 3. create:");
        Group group3 = new Group(group2);
        group3.setId(4);
        group3.setName("CREATED");
        GroupDao.create(group3);
        groups = GroupDao.findAll();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. update:");
        Group group4 = new Group(group3);
        group4.setName("UPDATED");
        GroupDao.update(group4);
        groups = GroupDao.findAll();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        GroupDao.delete(4);
        groups = GroupDao.findAll();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 6. findAllMembers:");
        List<User> users = GroupDao.findMembers(1);
        for(User user : users) {
            System.out.println(user);
        }

        System.out.println("\n*test 7. addMember:");
        GroupDao.addMember(3, 1);
        users = GroupDao.findMembers(1);
        for(User user : users) {
            System.out.println(user);
        }

        System.out.println("\n*test 8. deleteMember:");
        GroupDao.deleteMember(3, 1);
        users = GroupDao.findMembers(1);
        for(User user : users) {
            System.out.println(user);
        }

        System.out.println("\n*test 9. updateMembers:");
        users = new ArrayList<>();
        users.add(UserDao.findEntityById(3));
        users.add(UserDao.findEntityById(2));
        users.add(UserDao.findEntityById(1));
        GroupDao.updateGroupMembers(1, users);
        users = GroupDao.findMembers(1);
        for(User user : users) {
            System.out.println(user);
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void postTest() throws DaoException {
        System.out.println("5. POST_TEST:");
        List<Post> posts = PostDao.findAll();
        for(Post us : posts) {
            System.out.println(us.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void reportTest() throws DaoException {
        System.out.println("6. REPORT_TEST:");
        List<Report> reportList = ReportDao.findAll();
        for (Report us : reportList) {
            System.out.println(us.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void trackTest() throws DaoException {
        System.out.println("7. TRACK_TEST:");
        List<Track> tracks = TrackDao.findAll();
        for(Track us : tracks) {
            System.out.println(us.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    public static void main(String[] args) throws DaoException {
        //userTest();
        //albumTest();
        //genreTest();
        groupTest();
    }
}
