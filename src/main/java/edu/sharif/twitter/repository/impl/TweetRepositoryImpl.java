package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.PublicMessageRepository;
import edu.sharif.twitter.repository.TweetRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public  class TweetRepositoryImpl extends PublicMessageRepositoryImpl<Tweet>
    implements TweetRepository {

    public TweetRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Tweet> getEntityClass() {
        return Tweet.class;
    }

    @Override
    public void showPublicMessage(User user) {
        TypedQuery<Tweet> query = entityManager.createQuery(
                "from tweet t WHERE t.user.id    =: id", Tweet.class).setParameter("id", user.getId());

        query.getResultList().forEach(System.out::println);

    }


    @Override
    public void deleteById(Long id) {
        entityManager.createQuery(
                "delete from tweet as t where t.id =: id",
                Tweet.class
        ).setParameter("id",id);
    }
}
