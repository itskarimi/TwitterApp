package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.ChatRepository;
import edu.sharif.twitter.repository.DMRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DMRepositoryImpl extends BaseEntityRepositoryImpl<DM, Long>
            implements DMRepository {

    private ChatRepository chatRepository;

    public DMRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        chatRepository = new ChatRepositoryImpl(entityManager);
    }

    @Override
    public Class<DM> getEntityClass() {
        return DM.class;
    }


    @Override
    public List<DM> showDMs(User user) {
        List<Chat> chats = chatRepository.showChats(user);
        List<DM> dms = new ArrayList<>();
        for (Chat chat : chats)
            if (chat instanceof DM)
                dms.add((DM) chat);
        return dms;
    }
}
