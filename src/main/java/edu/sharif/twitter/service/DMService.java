package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.User;

import java.util.List;

public interface DMService extends BaseEntityService<DM, Long> {

    void newDM(User user1, User user2);

    List<User> showDMs(User user);

    DM findByUsers(User user1, User user2);
}
