package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.repository.GroupRepository;
import edu.sharif.twitter.service.GroupService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;
import org.w3c.dom.ls.LSOutput;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GroupServiceImpl extends BaseEntityServiceImpl<Group, Long, GroupRepository>
            implements GroupService {

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();
    public GroupServiceImpl(GroupRepository repository) {
        super(repository);
    }

    @Override
    public Group newGroup(User admin) {
        Group group = new Group();
        group.getGroupProfile().setName(new Input("enter your group name: ").getInputString());
        group.getGroupProfile().setDescription(new Input("enter your group description: ").getInputString());
        group.setAdmins(Arrays.asList(admin));
        List<User> members = new ArrayList<>();
        members.add(admin);
        while (true) {
            String username = new Input("enter a username or end: ").getInputString();
            if (username.equals("end"))
                break;
            SearchUserDto search = new SearchUserDto(username);
            User member = ApplicationContext.getUserService().findByUsername(search);
            if (Objects.isNull(member)) {
                System.out.println("User not found...");
            }
            else {
                if (members.contains(member))
                    System.out.println("user already added");
                else {
                    System.out.println(username + " added to the group");
                    members.add(member);
                }
            }
        }
        group.setMembers(members);
        group.setCreateDateTime(LocalDateTime.now());
        group.setLastUpdateDateTime(LocalDateTime.now());
        group.getGroupProfile().setGroup(group);
        transaction.begin();
        repository.save(group);
        transaction.commit();
        return group;
    }

    @Override
    public void addMember(Group group, User admin, User member) {
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return;
        }
        if (!group.getMembers().contains(member))
            group.getMembers().add(member);
    }

    @Override
    public void removeMember(Group group, User admin, User member) {
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return;
        }
        group.getMembers().remove(member);
        group.getAdmins().remove(member);
    }

    @Override
    public void promoteMember(Group group, User admin, User member) {
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return;
        }
        if (group.getAdmins().contains(member)) {
            System.out.println("this member is already an admin");
            return;
        }
        if (group.getMembers().contains(member))
            group.getAdmins().add(member);
    }

    @Override
    public void demoteMember(Group group, User admin, User member) {
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return;
        }
        if (group.getMembers().size() > 1)
            group.getAdmins().remove(member);
    }

    @Override
    public void changeProfile(Group group, User admin) {
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return;
        }
        group.getGroupProfile().setName(new Input("set the new name: ").getInputString());
        group.getGroupProfile().setDescription(new Input("set the new description").getInputString());
    }
}
