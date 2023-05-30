package fpmibsu.outloud.dao;

import fpmibsu.outloud.entitiy.Post;
import fpmibsu.outloud.exception.PersistentException;

import java.util.List;

public interface PostDao extends BaseDao<Post>{
    List<Post> findAllPosts() throws PersistentException;
    Post findPostById(Integer id) throws PersistentException;
    boolean deletePostById(Integer id) throws PersistentException;
    boolean isExist(Integer id) throws PersistentException;
    boolean createPost(Post entity) throws PersistentException;
    Post updatePost(Post entity) throws PersistentException;

}
