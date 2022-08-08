package edu.sharif.twitter.view.data;

import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.view.Home;
import eu.hansolo.tilesfx.skins.CharacterTileSkin;

import java.net.URL;
import java.util.ArrayList;

public class DataManager {
    private static final String THEME1_LOGIN = Home.class.getResource("css/theme1/login.css").toExternalForm();
    private static final String THEME1_BASICS = Home.class.getResource("css/theme1/basics.css").toExternalForm();
    private static final String THEME1_VIEWS = Home.class.getResource("css/theme1/views.css").toExternalForm();
    private static final String THEME1_CHART = Home.class.getResource("css/theme1/chart.css").toExternalForm();


    private static final String THEME2_LOGIN = Home.class.getResource("css/theme2/login.css").toExternalForm();
    private static final String THEME2_BASICS = Home.class.getResource("css/theme2/basics.css").toExternalForm();
    private static final String THEME2_VIEWS = Home.class.getResource("css/theme2/views.css").toExternalForm();
    private static final String THEME2_CHART = Home.class.getResource("css/theme2/chart.css").toExternalForm();


    private static final String THEME3_LOGIN = Home.class.getResource("css/theme3/login.css").toExternalForm();
    private static final String THEME3_BASICS = Home.class.getResource("css/theme3/basics.css").toExternalForm();
    private static final String THEME3_VIEWS = Home.class.getResource("css/theme3/views.css").toExternalForm();
    private static final String THEME3_CHART = Home.class.getResource("css/theme3/chart.css").toExternalForm();

    private static final String[] THEME1 = {THEME1_LOGIN, THEME1_BASICS, THEME1_VIEWS, THEME1_CHART};
    private static final String[] THEME2 = {THEME2_LOGIN, THEME2_BASICS, THEME2_VIEWS, THEME2_CHART};
    private static final String[] THEME3 = {THEME3_LOGIN, THEME3_BASICS, THEME3_VIEWS, THEME3_CHART};

    private static final String[][] THEMES = {THEME1, THEME2, THEME3};
    private static int themeNumber = 0;

    public static String[] THEME = THEMES[themeNumber];
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

    public static void clear() {
        user = null; tweet = null;
        comment = null; targetUser = null;
        message = null; chat = null;
        group = null; members.clear();
        mode = MessageMode.NULL;
    }

    public static void changeTheme() {
        THEME = THEMES[themeNumber++ % THEMES.length];
    }

}
