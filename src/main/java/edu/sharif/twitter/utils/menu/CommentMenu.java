package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentMenu extends Menu {
    private final User user;
    private final CommentService commentService;
    private final UserService userService;
    private final TweetService tweetService = ApplicationContext.getTweetService();

    public CommentMenu(User user, CommentService commentService, UserService userService) {
        super(new String[]{"Add Comment", "Show My Comments", "Edit Comment", "Delete Comment", "Back"});
        this.user = user;
        this.commentService = commentService;
        this.userService = userService;
    }

    public void runMenu() {
        User user1 = userService.findById(user.getId());
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    Tweet tweet = addComment();
                    if (!Objects.isNull(tweet)){
                        commentService.addComment(tweet , user1);
                        tweetService.save(tweet);

                    }
                    break;
                case 2:
//                    commentService.commentsOfUser(user.getId()).forEach(System.out::println);
                  user1.getTweets().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("choose your option for edit...");
                    Comment comment = editOrDeleteComment();
                    if (!Objects.isNull(comment))
                        new EditCommentMenu(comment, commentService , user1).runMenu();
                    break;
                case 4:
                    System.out.println("choose your option for delete...");
                    Comment deleteComment = editOrDeleteComment();
                    if (!Objects.isNull(deleteComment))
                        commentService.delete(deleteComment);
                    break;
                case 5:
                    return;
            }
        }
    }

    private Comment editOrDeleteComment() {
        User user1 = userService.findById(user.getId());
        List<Comment> comments = user1.getComments();
        List<String> commentText = new ArrayList<>();
        for (Comment comment : comments) {
            commentText.add(comment.getText());
        }
        commentText.add("Back");

        String[] texts = commentText.toArray(new String[0]);

        Comment comment = new ShowUsersInformation<Comment>(texts, comments, true).runMenu();
        comments.remove(comment);
        return comment;

    }

    private Tweet addComment() {
        List<List<Tweet>> tweets = new ArrayList<>();
        for (User showTweetAllOfUser : userService.showTweetAllOfUsers()) {
            tweets.add(showTweetAllOfUser.getTweets());
        }
        List<Tweet> tweetList = new ArrayList<>();
        for (List<Tweet> tweet : tweets) {
            tweetList.addAll(tweet);
        }

        List<String> texts = new ArrayList<>();
        for (Tweet tweet : tweetList) {
            texts.add(tweet.getText());
        }
        texts.add("Back");
        String[] textTweets = texts.toArray(new String[0]);
        System.out.println("Enter your tweet for add comment :");
       return new ShowUsersInformation<Tweet>(textTweets, tweetList, true).runMenu();

    }


}
