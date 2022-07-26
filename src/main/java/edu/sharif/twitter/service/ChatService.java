package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.ChatRepository;

import java.util.List;

public interface ChatService extends BaseEntityService<Chat, Long> {

    List<Chat> showChats(User user);
}
