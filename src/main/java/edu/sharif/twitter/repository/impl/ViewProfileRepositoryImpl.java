package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.ViewProfile;
import edu.sharif.twitter.repository.ViewProfileRepository;

import javax.persistence.EntityManager;

public class ViewProfileRepositoryImpl extends BaseEntityRepositoryImpl<ViewProfile, Long>
    implements ViewProfileRepository {
    public ViewProfileRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<ViewProfile> getEntityClass() {
        return ViewProfile.class;
    }
}
