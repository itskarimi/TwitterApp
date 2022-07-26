package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.repository.GroupRepository;

import javax.persistence.EntityManager;

public class GroupRepositoryImpl extends BaseEntityRepositoryImpl<Group, Long>
            implements GroupRepository {

    public GroupRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Group> getEntityClass() {
        return Group.class;
    }
}
