package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.ChatRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ChatRepositoryImpl extends BaseEntityRepositoryImpl<Chat, Long>
            implements ChatRepository {

    public ChatRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Chat> getEntityClass() {
        return Chat.class;
    }

    @Override
    public List<Chat> showChats(User user) {
        TypedQuery<Chat> query = entityManager.createQuery(
                "select c from Chat c join c.members m where m.id =: id", Chat.class).setParameter("id", user.getId());
        List<Chat> chats = query.getResultList();
        return chats;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery(
                "delete from Chat as c where c.id =: id",
                Chat.class
        ).setParameter("id",id);
    }
}
