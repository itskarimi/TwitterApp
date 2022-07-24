package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.DMRepository;
import edu.sharif.twitter.service.DMService;

import java.util.ArrayList;
import java.util.List;

public class DMServiceImpl extends BaseEntityServiceImpl<DM, Long, DMRepository>
            implements DMService {

    public DMServiceImpl(DMRepository repository) {
        super(repository);
    }

    @Override
    public void newDM(User user1, User user2) {
        DM dm = new DM();
        dm.setUser1(user1);
        dm.setUser2(user2);
        dm.setIsDeleted(false);
        save(dm);
    }

    @Override
    public List<User> showDMs(User user) {
        List<DM> list =  repository.showDMs(user);
        List<User> users = new ArrayList<>();
        for (DM dm : list) {
            if (dm.getUser1().equals(user))
                users.add(dm.getUser2());
            else
                users.add(dm.getUser1());
        }
        return users;
    }

    @Override
    public DM findByUsers(User user1, User user2) {
        List<DM> list = repository.showDMs(user1);
        for (DM dm : list)
            if (dm.getUser1().equals(user2) || dm.getUser2().equals(user2))
                return dm;
        return null;
    }


}
