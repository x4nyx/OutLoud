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
        List<User> users = UserDao.findAllUsers();
        for(User us : users) {
            System.out.println(us.toString());
        }
        System.out.println("\n*test 2. findbyid:");
        User user2 = UserDao.findUserById(3);
        System.out.println(user2.toString());

        System.out.println("\n*test 3. create:");
        User userToCreate = new User(user2);
        userToCreate.setId(4);
        userToCreate.setName("CREATED");
        UserDao.createUser(userToCreate);
        users = UserDao.findAllUsers();
        for(User us : users) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. delete:");
        UserDao.deleteUserById(4);
        users = UserDao.findAllUsers();
        for(User us : users) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. update:");
        User userUpdater = new User(user2);
        userUpdater.setName("UPDATED KID");
        UserDao.updateUser(userUpdater);
        users = UserDao.findAllUsers();
        for(User us : users) {
            System.out.println(us.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void albumTest() throws DaoException {
        System.out.println("2. ALBUM_TEST:");
        System.out.println("*test 1. output:");
        List<Album> albums = AlbumDao.findAllAlbums();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 2. find_by_id:");
        Album album2 = AlbumDao.findAlbumById(3);
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
        AlbumDao.createAlbum(album4);
        albums = AlbumDao.findAllAlbums();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        AlbumDao.deleteAlbumById(album4.getId());
        albums = AlbumDao.findAllAlbums();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 6. update:");
        Album album6 = new Album(album2);
        album6.setName("UPDATED");
        AlbumDao.updateAlbum(album6);
        albums = AlbumDao.findAllAlbums();
        for(Album us : albums) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 7. update_tracks:");
        List<Track> tracks = new ArrayList<>();
        tracks.add(TrackDao.findTrackById(1));
        tracks.add(TrackDao.findTrackById(2));
        tracks.add(TrackDao.findTrackById(3));
        AlbumDao.updateTrackList(2, tracks);
        tracks = AlbumDao.getTracksFromAlbum(2);
        if(tracks != null) {
            for (Track track : tracks) {
                System.out.println(track);
            }
        }

        System.out.println("\n*test 8. add_track:");
        AlbumDao.addTrackToAlbum(2, 1);
        tracks = AlbumDao.getTracksFromAlbum(2);
        if(tracks != null) {
            for (Track track : tracks) {
                System.out.println(track);
            }
        }

        System.out.println("\n*test 9. delete_track:");
        AlbumDao.deleteTrackFromAlbum(2, 1);
        tracks = AlbumDao.getTracksFromAlbum(2);
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
        List<Genre> genres = GenreDao.findAllGenres();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }
        System.out.println("\n*test 2. find_by_id:");
        Genre genre2 = GenreDao.findGenreById(2);
        System.out.println(genre2);

        System.out.println("\n*test 3. create:");
        Genre genre3 = new Genre(genre2);
        genre3.setId(4);
        genre3.setName("phonk");
        GenreDao.createGenre(genre3);
        genres = GenreDao.findAllGenres();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. update:");
        Genre genre4 = new Genre(genre3);
        genre4.setName("UPDATED");
        GenreDao.updateGenre(genre4);
        genres = GenreDao.findAllGenres();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        GenreDao.deleteGenreById(4);
        genres = GenreDao.findAllGenres();
        for(Genre us : genres) {
            System.out.println(us.toString());
        }

        System.out.println("-------------------------------------------------------------\n");
    }

    private static void groupTest() throws DaoException {
        System.out.println("4. GROUP_TEST:");
        System.out.println("*test 1. findAll:");
        List<Group> groups = GroupDao.findAllGroups();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 2. find_by_id:");
        Group group2 = GroupDao.findGroupById(3);
        System.out.println(group2);

        System.out.println("\n*test 3. create:");
        Group group3 = new Group(group2);
        group3.setId(4);
        group3.setName("CREATED");
        GroupDao.createGroup(group3);
        groups = GroupDao.findAllGroups();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. update:");
        Group group4 = new Group(group3);
        group4.setName("UPDATED");
        GroupDao.updateGroup(group4);
        groups = GroupDao.findAllGroups();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        GroupDao.deleteGroupById(4);
        groups = GroupDao.findAllGroups();
        for(Group us : groups) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 6. findAllMembers:");
        List<User> users = GroupDao.findMembersInGroup(1);
        for(User user : users) {
            System.out.println(user);
        }

        System.out.println("\n*test 7. addMember:");
        GroupDao.addMemberToGroup(3, 1);
        users = GroupDao.findMembersInGroup(1);
        for(User user : users) {
            System.out.println(user);
        }

        System.out.println("\n*test 8. deleteMember:");
        GroupDao.deleteMemberFromGroup(3, 1);
        users = GroupDao.findMembersInGroup(1);
        for(User user : users) {
            System.out.println(user);
        }

        System.out.println("\n*test 9. updateMembers:");
        users = new ArrayList<>();
        users.add(UserDao.findUserById(3));
        users.add(UserDao.findUserById(2));
        users.add(UserDao.findUserById(1));
        GroupDao.updateGroupMembers(1, users);
        users = GroupDao.findMembersInGroup(1);
        for(User user : users) {
            System.out.println(user);
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void postTest() throws DaoException {
        System.out.println("5. POST_TEST:");
        System.out.println("*test 1. findAll:");
        List<Post> posts = PostDao.findAllPosts();
        for(Post us : posts) {
            System.out.println(us.toString());
        }
        System.out.println("\n*test 2. find_by_id:");
        Post genre2 = PostDao.findPostById(2);
        System.out.println(genre2);

        System.out.println("\n*test 3. create:");
        Post genre3 = new Post(genre2);
        genre3.setId(4);
        genre3.setTitle("CREATED");
        PostDao.createPost(genre3);
        posts = PostDao.findAllPosts();
        for(Post us : posts) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. update:");
        Post genre4 = new Post(genre3);
        genre4.setTitle("UPDATED");
        PostDao.updatePost(genre4);
        posts = PostDao.findAllPosts();
        for(Post us : posts) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        PostDao.deletePostById(4);
        posts = PostDao.findAllPosts();
        for(Post us : posts) {
            System.out.println(us.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void reportTest() throws DaoException {
        System.out.println("6. REPORT_TEST:");
        System.out.println("*test 1. findAll:");
        List<Report> reportList = ReportDao.findAllReports();
        for (Report us : reportList) {
            System.out.println(us.toString());
        }
        System.out.println("\n*test 2. find_by_id:");
        Report genre2 = ReportDao.findReportById(2);
        System.out.println(genre2);

        System.out.println("\n*test 3. create:");
        Report genre3 = new Report(genre2);
        genre3.setId(4);
        genre3.setTitle("CREATED");
        ReportDao.createReport(genre3);
        reportList = ReportDao.findAllReports();
        for (Report us : reportList) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. update:");
        Report genre4 = new Report(genre3);
        genre4.setTitle("UPDATED");
        ReportDao.updateReport(genre4);
        reportList = ReportDao.findAllReports();
        for (Report us : reportList) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        ReportDao.deleteReportById(4);
        reportList = ReportDao.findAllReports();
        for (Report us : reportList) {
            System.out.println(us.toString());
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void trackTest() throws DaoException {
        System.out.println("7. TRACK_TEST:");
        System.out.println("*test 1. findAll:");
        List<Track> tracks = TrackDao.findAllTracks();
        for(Track us : tracks) {
            System.out.println(us.toString());
        }
        System.out.println("\n*test 2. find_by_id:");
        Track genre2 = TrackDao.findTrackById(2);
        System.out.println(genre2);

        System.out.println("\n*test 3. create:");
        Track genre3 = new Track(genre2);
        genre3.setId(4);
        genre3.setName("CREATED");
        TrackDao.createTrack(genre3);
        tracks = TrackDao.findAllTracks();
        for(Track us : tracks) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 4. update:");
        Track genre4 = new Track(genre3);
        genre4.setName("UPDATED");
        TrackDao.updateTrack(genre4);
        tracks = TrackDao.findAllTracks();
        for(Track us : tracks) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 5. delete:");
        TrackDao.deleteTrackById(4);
        tracks = TrackDao.findAllTracks();
        for(Track us : tracks) {
            System.out.println(us.toString());
        }

        System.out.println("\n*test 6. find_by_name:");
        List<Track> tracksByName = TrackDao.findTracksByName("top");
        for(Track track : tracksByName) {
            System.out.println(track);
        }
        System.out.println("-------------------------------------------------------------\n");
    }

    public static void main(String[] args) throws DaoException {
        //userTest();
        //albumTest();
        //genreTest();
        //groupTest();
        //postTest();
        //reportTest();
        //trackTest();
    }
}
