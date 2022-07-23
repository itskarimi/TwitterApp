package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.repository.CommentRepository;

import javax.persistence.EntityManager;

public class CommentRepositoryImpl extends BaseEntityRepositoryImpl<Comment, Long>
    implements CommentRepository {

    public CommentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

}
