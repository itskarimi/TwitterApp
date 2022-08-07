package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.util.List;

public interface PublicMessageService<T extends PublicMessage> extends BaseEntityService<T, Long> {

    T createPublicMessage(User user, PublicMessage repliedTo, String text);

    void editPublicMessage(T publicMessage);

    void showPublicMessage(User user);

    void deleteById(Long Id);

    List<Comment> getComments(T publicMessage);

    void showStats(PublicMessage publicMessage);
}
