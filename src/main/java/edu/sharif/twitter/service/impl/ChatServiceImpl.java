package edu.sharif.twitter.service.impl;

import antlr.GrammarAnalyzer;
import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.Group;
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

    @Override
    public String getName(User user, Chat chat) {
        if (chat instanceof Group)
            return ((Group) chat).getGroupProfile().getName();
        if (chat.getMembers().get(0).equals(user))
            return chat.getMembers().get(1).getUsername();
        return chat.getMembers().get(0).getUsername();
    }
}
