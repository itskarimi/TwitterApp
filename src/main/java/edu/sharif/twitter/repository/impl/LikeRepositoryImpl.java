package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.LikeRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class LikeRepositoryImpl extends BaseEntityRepositoryImpl<Like, Long>
        implements LikeRepository {

    public LikeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Like> getEntityClass() {
        return Like.class;
    }

    public Like findByUserAndPublicMessage(User user, PublicMessage publicMessage) {
        TypedQuery<Like> like = entityManager.createQuery(
                " select l from Like l where l.user.id =: user_id AND l.publicMessage.id =: public_message_id", Like.class
        ).setParameter("user_id", user.getId()).setParameter("public_message_id", publicMessage.getId());
        try {
            return like.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }
}
