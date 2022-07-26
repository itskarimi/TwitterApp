package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.repository.MessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MessageRepositoryImpl extends BaseEntityRepositoryImpl<Message, Long>
            implements MessageRepository {

    public MessageRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Message> getEntityClass() {
        return Message.class;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery(
                "delete from Message as m where m.id =: id",
                Message.class
        ).setParameter("id", id);
    }

    @Override
    public List<Message> showMessages(Chat chat) {
        TypedQuery<Message> query = entityManager.createQuery(
                "from Message m WHERE m.chat.id    =: id", Message.class).setParameter("id", chat.getId());

        List<Message> messages = query.getResultList();
        return messages;

    }
}
