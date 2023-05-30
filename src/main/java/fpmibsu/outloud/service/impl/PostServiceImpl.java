package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.GroupDao;
import fpmibsu.outloud.dao.PostDao;
import fpmibsu.outloud.dao.UserDao;
import fpmibsu.outloud.entitiy.Post;
import fpmibsu.outloud.service.PostService;

public class PostServiceImpl extends ServiceImpl implements PostService {
    public Post getPostById(Integer postId) throws PersistentException {
        PostDao postDao = transaction.createDao(PostDao.class);
        return postDao.findPostById(postId);
    }

    public boolean createPost(String text, String title, Integer creatorId, Integer groupId) throws PersistentException {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setText(text);
        newPost.setViewCount(0);
        UserDao userDao = transaction.createDao(UserDao.class);
        newPost.setCreator(userDao.findUserById(creatorId));
        if(newPost.getCreator() == null) {
            return false;
        }
        GroupDao groupDao = transaction.createDao(GroupDao.class);
        newPost.setGroup(groupDao.findGroupById(groupId));
        PostDao postDao = transaction.createDao(PostDao.class);
        return postDao.createPost(newPost);
    }
}
