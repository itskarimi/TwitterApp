package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public interface PublicMessageService<T extends PublicMessage> extends BaseEntityService<T, Long> {

    T createPublicMessage(User user, PublicMessage repliedTo, TextInputControl field);

    void editPublicMessage(T publicMessage);

    void showPublicMessage(User user);

    void deleteById(Long Id);

    void showStats(PublicMessage publicMessage);
}
