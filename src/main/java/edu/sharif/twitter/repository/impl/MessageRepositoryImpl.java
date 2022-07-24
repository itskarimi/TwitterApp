package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
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
        return null;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery(
                "delete from Message as m where m.id =: id",
                Message.class
        ).setParameter("id", id);
    }

    @Override
    public List<Message> showMessages(DM dm) {
        TypedQuery<Message> query = entityManager.createQuery(
                "from Message m WHERE m.dm.id    =: id", Message.class).setParameter("id", dm.getId());

        List<Message> messages = query.getResultList();
        return messages;

    }
}
