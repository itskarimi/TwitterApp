package edu.sharif.twitter.utils;

import edu.sharif.twitter.repository.CommentRepository;
import edu.sharif.twitter.repository.TweetRepository;
import edu.sharif.twitter.repository.UserRepository;
import edu.sharif.twitter.repository.impl.CommentRepositoryImpl;
import edu.sharif.twitter.repository.impl.TweetRepositoryImpl;
import edu.sharif.twitter.repository.impl.UserRepositoryImpl;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.service.impl.CommentServiceImpl;
import edu.sharif.twitter.service.impl.TweetServiceImpl;
import edu.sharif.twitter.service.impl.UserServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private static final UserRepository userRepository;

    private static final UserService userService;

    private static final TweetRepository tweetRepository;

    private static final TweetService tweetService;

    private static final CommentRepository commentRepository;

    private static final CommentService commentService;




    static {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository);

        tweetRepository = new TweetRepositoryImpl(entityManager);
        tweetService = new TweetServiceImpl(tweetRepository);

        commentRepository = new CommentRepositoryImpl(entityManager);
        commentService = new CommentServiceImpl(commentRepository);

    }

    public static UserService getUserService() {
        return userService;
    }

    public static TweetService getTweetService() {
        return tweetService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

}
