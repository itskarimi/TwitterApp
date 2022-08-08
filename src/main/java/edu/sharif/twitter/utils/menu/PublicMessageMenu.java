package edu.sharif.twitter.utils.menu;


import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.service.LikeService;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.utils.ApplicationContext;

import java.util.*;

public class PublicMessageMenu<T extends PublicMessage> extends Menu{
    private final User user;
    private final T selected;
    private final PublicMessageService<T> publicMessageService;
    private final LikeService likeService = ApplicationContext.getLikeService();
    private Like like;
    private final Class<T> type;

    public PublicMessageMenu(User user, T selected, Class<T> type) {
        super(new ArrayList<>(Arrays.asList("Like", "Show likes", "Comment", "Show comments", "Back")));
        this.user = user;
        this.selected = selected;
        this.type = type;
        publicMessageService = type == Tweet.class
                ? (PublicMessageService<T>) ApplicationContext.getTweetService()
                : (PublicMessageService<T>) ApplicationContext.getCommentService();
        updateLikeStatus();

        if (type == Tweet.class) {
            if (((Tweet)selected).getUser().equals(user)) {
                items.add("Edit");
                items.add("Delete");
            }
            if (user.getIsBusiness() && ((Tweet)selected).getUser().equals(user)) {
                items.add("Show stats");
            }
        } else {
            if (((Comment)selected).getUser().equals(user)) {
                items.add("Edit");
                items.add("Delete");
            }
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
                    new SelectMenu<>(user, getLikesUsers()).runMenu();
                    return;
                case 3:
                    new AddPostMenu<>(user, selected, Comment.class).runMenu();
                    break;
                case 4:
                    new SelectMenu<>(user, selected.getComments()).runMenu();
                case 5:
                    return;
                case 6:
                    new EditPublicMessageMenu<>(user, selected, publicMessageService).runMenu();
                    break;
                case 7:
                    publicMessageService.delete(type.cast(selected));
                    return;
                case 8:
                    publicMessageService.showStats(selected);
                    break;
            }
        }
    }

    private void updateLikeStatus() {
        this.like = likeService.findByUserAndPublicMessage(user, selected);
        items.set(0, like == null ? "Like" : "Unlike");
    }

    private List<User> getLikesUsers() {
        List<Like> likes = selected.getLikes();
        likes.sort(Comparator.comparing(Like::getCreateDateTime));
        Collections.reverse(likes);

        List<User> users = new ArrayList<>();
        for (Like l : likes) {
            users.add(l.getUser());
        }

        return users;
    }
}
