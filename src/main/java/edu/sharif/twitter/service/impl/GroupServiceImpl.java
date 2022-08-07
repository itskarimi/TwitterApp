package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.repository.GroupRepository;
import edu.sharif.twitter.service.GroupService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.w3c.dom.ls.LSOutput;

import javax.imageio.ImageIO;
import javax.persistence.EntityTransaction;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    public Group newGroup(User admin, String name, String description, List<User> members, Image image) throws IOException {
        Group group = new Group();
        group.getGroupProfile().setName(name);
        group.getGroupProfile().setDescription(description);
        /*group.getGroupProfile().setName(new Input("enter your group name: ").getInputString());
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
        }*/
        group.setAdmins(Arrays.asList(admin));
        group.getMembers().addAll(members);
        group.setCreateDateTime(LocalDateTime.now());
        group.setLastUpdateDateTime(LocalDateTime.now());
        group.getGroupProfile().setProfileImage(image);
        group.getGroupProfile().setGroup(group);
        for (User member : members)
            member.getChats().add(group);
        admin.getAdminChats().add(group);
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
        if (!group.getMembers().contains(member)) {
            group.getMembers().add(member);
            member.getChats().add(group);

            transaction.begin();
            repository.save(group);
            transaction.commit();
        }
    }

    @Override
    public boolean removeMember(Group group, User admin, User member) {
        if (admin.equals(member)) {
            group.getMembers().remove(member);
            group.getAdmins().remove(member);
            member.getChats().remove(group);
            member.getAdminChats().remove(group);

            transaction.begin();
            repository.save(group);
            transaction.commit();
            return true;
        }
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return false;
        }
        group.getMembers().remove(member);
        group.getAdmins().remove(member);
        member.getChats().remove(group);
        member.getAdminChats().remove(group);

        transaction.begin();
        repository.save(group);
        transaction.commit();
        return true;
    }

    @Override
    public boolean promoteMember(Group group, User admin, User member) {
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return false;
        }
        if (group.getAdmins().contains(member)) {
            System.out.println("this member is already an admin");
            return false;
        }
        if (group.getMembers().contains(member)) {
            group.getAdmins().add(member);
            member.getAdminChats().add(group);

            transaction.begin();
            repository.save(group);
            transaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean demoteMember(Group group, User admin, User member) {
        if (!group.getAdmins().contains(admin)) {
            System.out.println("you are not admin!");
            return false;
        }
        if (group.getMembers().size() > 1) {
            group.getAdmins().remove(member);
            member.getAdminChats().remove(group);

            transaction.begin();
            repository.save(group);
            transaction.commit();
            return true;
        }
        return false;
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

    @Override
    public Image getProfileImage(Group group) throws IOException {
        byte[] byteArray = group.getGroupProfile().getProfileImage();

        ByteArrayInputStream inStream = new ByteArrayInputStream(byteArray);

        BufferedImage bufferedImage = ImageIO.read(inStream);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    @Override
    public void setProfileImage(Group group, Image image) throws IOException {

        group.getGroupProfile().setProfileImage(image);
        this.save(group);
    }
}
