package fpmibsu.outloud.service;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.entitiy.Post;

public interface PostService extends Service {

    Post getPostById(Integer postId) throws PersistentException;
    boolean createPost(String text, String title, Integer creatorId, Integer groupId) throws PersistentException;
}
