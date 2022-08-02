package edu.sharif.twitter.utils.menu;


import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.service.LikeService;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.utils.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

public class PublicMessageMenu<T extends PublicMessage> extends Menu{
    private final User user;
    private final PublicMessage selected;
    private final PublicMessageService<T> publicMessageService;
    private final LikeService likeService = ApplicationContext.getLikeService();
    private Like like;

    public PublicMessageMenu(User user, PublicMessage selected, Class<T> type) {
        super(new ArrayList<>(Arrays.asList("Like", "Comment", "Show comments", "Back")));
        this.user = user;
        this.selected = selected;
        publicMessageService = type == Tweet.class
                ? (PublicMessageService<T>) ApplicationContext.getTweetService()
                : (PublicMessageService<T>) ApplicationContext.getCommentService();
        updateLikeStatus();

        if (type == Tweet.class) {
            if (user.getIsBusiness() && ((Tweet)selected).getUser().equals(user)) {
                items.add("Show stats");
            }
        } else {
            if (user.getIsBusiness() && ((Comment)selected).getUser().equals(user)) {
                items.add("Show stats");
            }
        }
    }


    public void runMenu() {
        while (true){
            print();
            switch (chooseOperation()) {
                case 1:
                    if (like == null) {
                        likeService.addLike(user, selected);
                    } else {
                        likeService.delete(like);
                    }
                    updateLikeStatus();
                    break;
                case 2:
                    new AddPostMenu<>(user, selected, Comment.class).runMenu();
                    break;
                case 3:
                    new SelectMenu<>(user, selected.getComments()).runMenu();
                case 4:
                    return;
                case 5:
                    publicMessageService.showStats(selected);
            }
        }
    }

    private void updateLikeStatus() {
        this.like = likeService.findByUserAndPublicMessage(user, selected);
        items.set(0, like == null ? "Like" : "Unlike");
    }
}
