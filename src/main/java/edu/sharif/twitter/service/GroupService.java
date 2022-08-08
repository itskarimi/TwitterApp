package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.List;

public interface GroupService extends BaseEntityService<Group, Long> {

    Group newGroup(User admin, String name, String description, List<User> members, Image image) throws IOException;

    void addMember(Group group, User admin, User member);

    boolean removeMember(Group group, User admin, User member);

    void promoteMember(Group group, User admin, User member);

    void demoteMember(Group group, User admin, User member);

    void changeProfile(Group group, User admin);

    Image getProfileImage(Group group) throws IOException;

    void setProfileImage(Group group, Image image) throws IOException;
}
