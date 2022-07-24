package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.User;

import java.util.List;

public interface MessageService extends BaseEntityService<Message, Long> {

    Message addMessage(User user, DM dm, Boolean isReply);

    void editMessage(Message message);

    List<Message> showMessages(DM dm);

    void deleteById(Long id);
}
