package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.ChatRepository;
import edu.sharif.twitter.service.ChatService;

import java.util.List;

public class ChatServiceImpl extends BaseEntityServiceImpl<Chat, Long, ChatRepository>
            implements ChatService {
    public ChatServiceImpl(ChatRepository repository) {
        super(repository);
    }

    @Override
    public List<Chat> showChats(User user) {
        return repository.showChats(user);
    }
}
