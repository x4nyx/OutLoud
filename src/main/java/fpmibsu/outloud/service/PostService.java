package fpmibsu.outloud.service;

import fpmibsu.outloud.dao.DaoException;
import fpmibsu.outloud.dao.PostDao;
import fpmibsu.outloud.entitiy.Post;

public class PostService {
    public Post getPostById(Integer postId) throws DaoException {
        PostDao postDao = new PostDao();
        postDao.createConnection();
        return postDao.findPostById(postId);
    }

    public boolean createPost(String text, String title, Integer creatorId, Integer groupId) throws DaoException {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setText(text);
        newPost.setViewCount(0);
        UserService userService = new UserService();
        newPost.setCreator(userService.getUserById(creatorId));
        if(newPost.getCreator() == null) {
            return false;
        }
        GroupService groupService = new GroupService();
        newPost.setGroup(groupService.getGroupById(groupId));
        PostDao postDao = new PostDao();
        postDao.createConnection();
        return postDao.createPost(newPost);
    }
}
