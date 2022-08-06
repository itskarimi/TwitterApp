package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;

import java.util.List;

public interface GroupService extends BaseEntityService<Group, Long> {

    Group newGroup(User admin, String name, String description, List<User> members);

    void addMember(Group group, User admin, User member);

    void removeMember(Group group, User admin, User member);

    void promoteMember(Group group, User admin, User member);

    void demoteMember(Group group, User admin, User member);

    void changeProfile(Group group, User admin);
}
