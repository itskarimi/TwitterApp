package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.DateCount;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import javafx.scene.image.Image;

import java.util.List;

public interface TweetService extends PublicMessageService<Tweet> {

    void setImage(Tweet tweet, Image image);
    void deleteImage(Tweet tweet);
    Image getImage(Tweet tweet);

    List<DateCount> getViewStat(PublicMessage publicMessage);
    List<DateCount> getLikeStat(PublicMessage publicMessage);
}
