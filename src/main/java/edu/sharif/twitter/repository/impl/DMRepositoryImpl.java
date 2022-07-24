package edu.sharif.twitter.repository.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.DMRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DMRepositoryImpl extends BaseEntityRepositoryImpl<DM, Long>
        implements DMRepository {

    public DMRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<DM> getEntityClass() {
        return DM.class;
    }

    @Override
    public List<DM> showDMs(User user) {
        TypedQuery<DM> query = entityManager.createQuery(
                "from DM dm WHERE dm.user1.id =: id OR dm.user2.id =: id", DM.class).setParameter("id", user.getId());

        return query.getResultList();
    }
}
