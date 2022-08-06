package edu.sharif.twitter.view.data;

import edu.sharif.twitter.entity.*;
import eu.hansolo.tilesfx.skins.CharacterTileSkin;

import java.util.ArrayList;

public class DataManager {
    private static User user;
    private static Tweet tweet;
    private static Comment comment;
    private static User targetUser;
    private static Message message;
    private static Chat chat;
    private static MessageMode mode;
    private static Group group;
    private static ArrayList<User> members = new ArrayList<>();

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        DataManager.user = user;
    }

    public static Tweet getTweet() {
        return tweet;
    }

    public static void setTweet(Tweet tweet) {
        DataManager.tweet = tweet;
    }

    public static Comment getComment() {
        return comment;
    }

    public static void setComment(Comment comment) {
        DataManager.comment = comment;
    }

    public static User getTargetUser() {
        return targetUser;
    }

    public static void setTargetUser(User targetUser) {
        DataManager.targetUser = targetUser;
    }

    public static Chat getChat() {
        return chat;
    }

    public static void setChat(Chat chat) {
        DataManager.chat = chat;
    }

    public static Message getMessage() {
        return message;
    }

    public static MessageMode getMode() {
        return mode;
    }

    public static void setMessage(Message message, MessageMode mode) {
        DataManager.message = message;
        DataManager.mode = mode;
    }

    public static Group getGroup() {
        return group;
    }

    public static void setGroup(Group group) {
        DataManager.group = group;
    }

    public static ArrayList<User> getMembers() {
        return members;
    }

}
