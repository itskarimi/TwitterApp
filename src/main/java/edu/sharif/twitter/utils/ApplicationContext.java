package edu.sharif.twitter.utils;

import edu.sharif.twitter.repository.*;
import edu.sharif.twitter.repository.impl.*;
import edu.sharif.twitter.service.*;
import edu.sharif.twitter.service.impl.*;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private static final UserRepository userRepository;

    private static final UserService userService;

    private static final TweetRepository tweetRepository;

    private static final TweetService tweetService;

    private static final CommentRepository commentRepository;

    private static final CommentService commentService;


    private static final DMRepository dmRepository;

    private static final DMService dmService;

    private static final MessageRepository messageRepository;
    private static final MessageService messageService;




    static {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository);

        tweetRepository = new TweetRepositoryImpl(entityManager);
        tweetService = new TweetServiceImpl(tweetRepository);

        commentRepository = new CommentRepositoryImpl(entityManager);
        commentService = new CommentServiceImpl(commentRepository);

        dmRepository = new DMRepositoryImpl(entityManager);
        dmService = new DMServiceImpl(dmRepository);

        messageRepository = new MessageRepositoryImpl(entityManager);
        messageService = new MessageServiceImpl(messageRepository);
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

    public static DMService getDmService() {
        return dmService;
    }

    public static MessageService getMessageService() {
        return messageService;
    }
}
