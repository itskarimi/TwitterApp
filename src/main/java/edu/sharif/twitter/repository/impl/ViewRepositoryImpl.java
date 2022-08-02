package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.View;
import edu.sharif.twitter.repository.ViewRepository;

import javax.persistence.EntityManager;

public class ViewRepositoryImpl extends BaseEntityRepositoryImpl<View, Long>
    implements ViewRepository {
    public ViewRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<View> getEntityClass() {
        return View.class;
    }
}
