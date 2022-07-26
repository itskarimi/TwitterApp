package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.User;

import java.util.List;

public interface MessageService extends BaseEntityService<Message, Long> {

    Message addMessage(User user, Chat chat);

    Message addReply(User user, Message message);

    void editMessage(Message message);

    List<Message> showMessages(Chat chat);

    void deleteById(Long id);
}
