package edu.sharif.twitter.utils;

import edu.sharif.twitter.entity.Group;
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

    private static final LikeRepository likeRepository;
    private static final LikeService likeService;

    private static final DMRepository dmRepository;

    private static final DMService dmService;

    private static final MessageRepository messageRepository;
    private static final MessageService messageService;

    private static final ChatRepository chatRepository;
    private static final ChatService chatService;

    private static final GroupRepository groupRepository;
    private static final GroupService groupService;

    private static final ViewRepository viewRepository;
    private static final ViewService viewService;

    private static final ViewProfileRepository viewProfileRepository;
    private static final ViewProfileService viewProfileService;


    static {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository);

        tweetRepository = new TweetRepositoryImpl(entityManager);
        tweetService = new TweetServiceImpl(tweetRepository);

        commentRepository = new CommentRepositoryImpl(entityManager);
        commentService = new CommentServiceImpl(commentRepository);

        likeRepository = new LikeRepositoryImpl(entityManager);
        likeService = new LikeServiceImpl(likeRepository);

        dmRepository = new DMRepositoryImpl(entityManager);
        dmService = new DMServiceImpl(dmRepository);

        messageRepository = new MessageRepositoryImpl(entityManager);
        messageService = new MessageServiceImpl(messageRepository);

        chatRepository = new ChatRepositoryImpl(entityManager);
        chatService = new ChatServiceImpl(chatRepository);

        groupRepository = new GroupRepositoryImpl(entityManager);
        groupService = new GroupServiceImpl(groupRepository);

        viewRepository = new ViewRepositoryImpl(entityManager);
        viewService = new ViewServiceImpl(viewRepository);

        viewProfileRepository = new ViewProfileRepositoryImpl(entityManager);
        viewProfileService = new ViewProfileServiceImpl(viewProfileRepository);
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

    public static LikeService getLikeService() {
        return likeService;
    }

    public static DMService getDmService() {
        return dmService;
    }

    public static MessageService getMessageService() {
        return messageService;
    }

    public static ChatService getChatService() {
        return chatService;
    }

    public static GroupService getGroupService() {
        return groupService;
    }

    public static ViewService getViewService() {
        return viewService;
    }

    public static ViewProfileService getViewProfileService() {
        return viewProfileService;
    }
}
