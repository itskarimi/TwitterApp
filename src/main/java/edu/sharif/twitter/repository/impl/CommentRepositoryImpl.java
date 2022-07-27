package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.CommentRepository;
import edu.sharif.twitter.repository.PublicMessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CommentRepositoryImpl extends PublicMessageRepositoryImpl<Comment>
    implements CommentRepository {

    public CommentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    public void showPublicMessage(User user) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "from Comment t WHERE t.user.id    =: id", Comment.class).setParameter("id", user.getId());

        query.getResultList().forEach(System.out::println);

    }


    @Override
    public void deleteById(Long id) {
        entityManager.createQuery(
                "delete from Comment as t where t.id =: id",
                Comment.class
        ).setParameter("id",id);
    }
}



