//package fpmibsu.outloud;
//
//import fpmibsu.outloud.connectioncreator.ConnectionCreator;
//import fpmibsu.outloud.dao.*;
//import fpmibsu.outloud.entitiy.*;
//import fpmibsu.outloud.exception.PersistentException;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//// ВАЖНО!!!!!! тесты предполагают наличие в каждой таблице базы данных 3 поля с id 1 2 3
//
//public class Runner {
//    private static void userTest(UserDao userDao) throws PersistentException {
//        System.out.println("1. USER_TEST:");
//        System.out.println("*test 1. output:");
//        List<User> users = userDao.findAllUsers();
//        for(User us : users) {
//            System.out.println(us.view());
//        }
//        System.out.println("\n*test 2. findbyid:");
//        User user2 = userDao.findUserById(1);
//        System.out.println(user2.view());
//
//        System.out.println("\n*test 3. create:");
//        User userToCreate = new User(user2);
//        userToCreate.setName("CREATED");
//        userDao.createUser(userToCreate);
//        users = userDao.findAllUsers();
//        for(User us : users) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 4. update:");
//        User userUpdater = new User(userToCreate);
//        userUpdater.setName("UPDATED");
//        userDao.updateUser(userUpdater);
//        users = userDao.findAllUsers();
//        for(User us : users) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 5. delete:");
//        userDao.deleteUserById(userUpdater.getId());
//        users = userDao.findAllUsers();
//        for(User us : users) {
//            System.out.println(us.view());
//        }
//        System.out.println("-------------------------------------------------------------\n");
//    }
//
//    private static void albumTest(AlbumDao albumDao, TrackDao trackDao) throws PersistentException {
//        System.out.println("2. ALBUM_TEST:");
//        System.out.println("*test 1. output:");
//        List<Album> albums = albumDao.findAllAlbums();
//        for(Album us : albums) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 2. find_by_id:");
//        Album album2 = albumDao.findAlbumById(1);
//        System.out.println(album2.view());
//
//        System.out.println("\n*test 3. find_by_name:");
//        List<Album> album3 = albumDao.findAlbumByName("date");
//        for(Album us : album3) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 4. create:");
//        Album album4 = new Album(album2);
//        album4.setName("CREATE");
//        albumDao.createAlbum(album4);
//        albums = albumDao.findAllAlbums();
//        for(Album us : albums) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 5. update:");
//        Album album6 = new Album(album4);
//        album6.setName("UPDATED");
//        albumDao.updateAlbum(album6);
//        albums = albumDao.findAllAlbums();
//        for(Album us : albums) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 6. delete:");
//        albumDao.deleteAlbumById(album6.getId());
//        albums = albumDao.findAllAlbums();
//        for(Album us : albums) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 7. update_tracks:");
//        List<Track> tracks = new ArrayList<>();
//        tracks.add(trackDao.findTrackById(1));
//        tracks.add(trackDao.findTrackById(2));
//        tracks.add(trackDao.findTrackById(3));
//        albumDao.updateTrackList(1, tracks);
//        tracks = albumDao.getTracksFromAlbum(1);
//        if(tracks != null) {
//            for (Track track : tracks) {
//                System.out.println(track.view());
//            }
//        }
//
//        System.out.println("\n*test 8. add_track:");
//        albumDao.addTrackToAlbum(1, 1);
//        tracks = albumDao.getTracksFromAlbum(1);
//        if(tracks != null) {
//            for (Track track : tracks) {
//                System.out.println(track.view());
//            }
//        }
//
//        System.out.println("\n*test 9. delete_track:");
//        albumDao.deleteTrackFromAlbum(1, 1);
//        tracks = albumDao.getTracksFromAlbum(1);
//        if(tracks != null) {
//            for (Track track : tracks) {
//                System.out.println(track.view());
//            }
//        }
//        System.out.println("-------------------------------------------------------------\n");
//    }
//
//    private static void genreTest(GenreDao genreDao) throws PersistentException {
//        System.out.println("3. GENRE_TEST:");
//        System.out.println("*test 1. findAll:");
//        List<Genre> genres = genreDao.findAllGenres();
//        for(Genre us : genres) {
//            System.out.println(us.view());
//        }
//        System.out.println("\n*test 2. find_by_id:");
//        Genre genre2 = genreDao.findGenreById(2);
//        System.out.println(genre2.view());
//
//        System.out.println("\n*test 3. create:");
//        Genre genre3 = new Genre(genre2);
//        genre3.setName("phonk");
//        genreDao.createGenre(genre3);
//        genres = genreDao.findAllGenres();
//        for(Genre us : genres) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 4. update:");
//        Genre genre4 = new Genre(genre3);
//        genre4.setName("UPDATED");
//        genreDao.updateGenre(genre4);
//        genres = genreDao.findAllGenres();
//        for(Genre us : genres) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 5. delete:");
//        genreDao.deleteGenreById(genre4.getId());
//        genres = genreDao.findAllGenres();
//        for(Genre us : genres) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("-------------------------------------------------------------\n");
//    }
//
//    private static void groupTest(GroupDao groupDao, UserDao userDao) throws PersistentException {
//        System.out.println("4. GROUP_TEST:");
//        System.out.println("*test 1. findAll:");
//        List<Group> groups = groupDao.findAllGroups();
//        for(Group us : groups) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 2. find_by_id:");
//        Group group2 = groupDao.findGroupById(1);
//        System.out.println(group2.view());
//
//        System.out.println("\n*test 3. create:");
//        Group group3 = new Group(group2);
//        group3.setName("CREATED");
//        groupDao.createGroup(group3);
//        groups = groupDao.findAllGroups();
//        for(Group us : groups) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 4. update:");
//        Group group4 = new Group(group3);
//        group4.setName("UPDATED");
//        groupDao.updateGroup(group4);
//        groups = groupDao.findAllGroups();
//        for(Group us : groups) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 5. delete:");
//        groupDao.deleteGroupById(group4.getId());
//        groups = groupDao.findAllGroups();
//        for(Group us : groups) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 6. findAllMembers:");
//        List<User> users = groupDao.findMembersInGroup(1);
//        for(User user : users) {
//            System.out.println(user.view());
//        }
//
//        System.out.println("\n*test 7. addMember:");
//        groupDao.addMemberToGroup(2, 1);
//        users = groupDao.findMembersInGroup(1);
//        for(User user : users) {
//            System.out.println(user.view());
//        }
//
//        System.out.println("\n*test 8. deleteMember:");
//        groupDao.deleteMemberFromGroup(2, 1);
//        users = groupDao.findMembersInGroup(1);
//        for(User user : users) {
//            System.out.println(user.view());
//        }
//
//        System.out.println("\n*test 9. updateMembers:");
//        users = new ArrayList<>();
//        users.add(userDao.findUserById(3));
//        users.add(userDao.findUserById(2));
//        users.add(userDao.findUserById(1));
//        groupDao.updateGroupMembers(1, users);
//        users = groupDao.findMembersInGroup(1);
//        for(User user : users) {
//            System.out.println(user.view());
//        }
//        System.out.println("-------------------------------------------------------------\n");
//    }
//
//    private static void postTest(PostDao postDao) throws PersistentException {
//        System.out.println("5. POST_TEST:");
//        System.out.println("*test 1. findAll:");
//        List<Post> posts = postDao.findAllPosts();
//        for(Post us : posts) {
//            System.out.println(us.view());
//        }
//        System.out.println("\n*test 2. find_by_id:");
//        Post genre2 = postDao.findPostById(1);
//        System.out.println(genre2.view());
//
//        System.out.println("\n*test 3. create:");
//        Post genre3 = new Post(genre2);
//        genre3.setTitle("CREATED");
//        postDao.createPost(genre3);
//        posts = postDao.findAllPosts();
//        for(Post us : posts) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 4. update:");
//        Post genre4 = new Post(genre3);
//        genre4.setTitle("UPDATED");
//        postDao.updatePost(genre4);
//        posts = postDao.findAllPosts();
//        for(Post us : posts) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 5. delete:");
//        postDao.deletePostById(genre4.getId());
//        posts = postDao.findAllPosts();
//        for(Post us : posts) {
//            System.out.println(us.view());
//        }
//        System.out.println("-------------------------------------------------------------\n");
//    }
//
//    private static void reportTest(ReportDao reportDao) throws PersistentException {
//        System.out.println("6. REPORT_TEST:");
//        System.out.println("*test 1. findAll:");
//        List<Report> reportList = reportDao.findAllReports();
//        for (Report us : reportList) {
//            System.out.println(us.view());
//        }
//        System.out.println("\n*test 2. find_by_id:");
//        Report genre2 = reportDao.findReportById(1);
//        System.out.println(genre2.view());
//
//        System.out.println("\n*test 3. create:");
//        Report genre3 = new Report(genre2);
//        genre3.setTitle("CREATED");
//        reportDao.createReport(genre3);
//        reportList = reportDao.findAllReports();
//        for (Report us : reportList) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 4. update:");
//        Report genre4 = new Report(genre3);
//        genre4.setTitle("UPDATED");
//        reportDao.updateReport(genre4);
//        reportList = reportDao.findAllReports();
//        for (Report us : reportList) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 5. delete:");
//        reportDao.deleteReportById(genre4.getId());
//        reportList = reportDao.findAllReports();
//        for (Report us : reportList) {
//            System.out.println(us.view());
//        }
//        System.out.println("-------------------------------------------------------------\n");
//    }
//
//    private static void trackTest(TrackDao trackDao) throws PersistentException {
//        System.out.println("7. TRACK_TEST:");
//        System.out.println("*test 1. findAll:");
//        List<Track> tracks = trackDao.findAllTracks();
//        for(Track us : tracks) {
//            System.out.println(us.view());
//        }
//        System.out.println("\n*test 2. find_by_id:");
//        Track genre2 = trackDao.findTrackById(1);
//        System.out.println(genre2.view());
//
//        System.out.println("\n*test 3. create:");
//        Track genre3 = new Track(genre2);
//        genre3.setName("CREATED");
//        trackDao.createTrack(genre3);
//        tracks = trackDao.findAllTracks();
//        for(Track us : tracks) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 4. update:");
//        Track genre4 = new Track(genre3);
//        genre4.setName("UPDATED");
//        trackDao.updateTrack(genre4);
//        tracks = trackDao.findAllTracks();
//        for(Track us : tracks) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 5. delete:");
//        trackDao.deleteTrackById(genre4.getId());
//        tracks = trackDao.findAllTracks();
//        for(Track us : tracks) {
//            System.out.println(us.view());
//        }
//
//        System.out.println("\n*test 6. find_by_name:");
//        List<Track> tracksByName = trackDao.findTracksByName("top");
//        for(Track track : tracksByName) {
//            System.out.println(track.view());
//        }
//        System.out.println("-------------------------------------------------------------\n");
//    }
//
//    public static void main(String[] args) {
//        AlbumDao albumDao = new AlbumDao();
//        GenreDao genreDao = new GenreDao();
//        GroupDao groupDao = new GroupDao();
//        PostDao postDao = new PostDao();
//        ReportDao reportDao = new ReportDao();
//        TrackDao trackDao = new TrackDao();
//        UserDao userDao = new UserDao();
//        try {
//            albumDao = new AlbumDao(ConnectionCreator.createConnection());
//            genreDao = new GenreDao(ConnectionCreator.createConnection());
//            groupDao = new GroupDao(ConnectionCreator.createConnection());
//            postDao = new PostDao(ConnectionCreator.createConnection());
//            reportDao = new ReportDao(ConnectionCreator.createConnection());
//            trackDao = new TrackDao(ConnectionCreator.createConnection());
//            userDao = new UserDao(ConnectionCreator.createConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            userTest(userDao);
//            albumTest(albumDao, trackDao);
//            genreTest(genreDao);
//            groupTest(groupDao, userDao);
//            postTest(postDao);
//            reportTest(reportDao);
//            trackTest(trackDao);
//        } catch (PersistentException e) {
//            e.printStackTrace();        }
//    }
//}
