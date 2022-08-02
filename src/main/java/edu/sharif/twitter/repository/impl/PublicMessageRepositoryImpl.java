package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.repository.PublicMessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class PublicMessageRepositoryImpl<T extends PublicMessage> extends BaseEntityRepositoryImpl<T, Long>
        implements PublicMessageRepository<T> {

    public PublicMessageRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public abstract Class<T> getEntityClass();

    public abstract void showPublicMessage(User user);

    public abstract void deleteById(Long id);

    public List<DateCount> getViewCountPerDay(PublicMessage publicMessage) {
        TypedQuery<DateCount> dateCounts = entityManager.createQuery(
                "SELECT NEW edu.sharif.twitter.entity.DateCount(date(v.createDateTime), COUNT(*))\n" +
                        "FROM View v\n" +
                        "WHERE v.viewed.id =: id\n" +
                        "GROUP BY date(v.createDateTime)", DateCount.class
        ).setParameter("id", publicMessage.getId());
        return dateCounts.getResultList();
    }

    public List<DateCount> getLikeCountPerDay(PublicMessage publicMessage) {
        TypedQuery<DateCount> dateCounts = entityManager.createQuery(
                "SELECT NEW edu.sharif.twitter.entity.DateCount(date(l.createDateTime), COUNT(*))\n" +
                        "FROM Like l\n" +
                        "WHERE l.publicMessage.id =: id\n" +
                        "GROUP BY date(l.createDateTime)", DateCount.class
        ).setParameter("id", publicMessage.getId());
        return dateCounts.getResultList();
    }
}
