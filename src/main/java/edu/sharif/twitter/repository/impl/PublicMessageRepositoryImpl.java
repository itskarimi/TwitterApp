package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.PublicMessageRepository;

import javax.persistence.EntityManager;

public abstract class PublicMessageRepositoryImpl<T extends PublicMessage> extends BaseEntityRepositoryImpl<T, Long>
        implements PublicMessageRepository<T> {

    public PublicMessageRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public abstract Class<T> getEntityClass();

    public abstract void showPublicMessage(User user);
    public abstract void deleteById(Long id);
}
