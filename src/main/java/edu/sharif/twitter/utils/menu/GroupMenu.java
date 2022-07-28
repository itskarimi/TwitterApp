package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.service.GroupService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.ShowEntities;
import edu.sharif.twitter.utils.input.Input;

import java.util.ArrayList;
import java.util.List;

public class GroupMenu extends Menu{

    private User user;

    private Group group;

    private GroupService groupService = ApplicationContext.getGroupService();

    private List<User> members;

    private ShowEntities<User> showMembers;

    public GroupMenu(User user, Group group) {
        super(new String[] {"show previous", "show next", "select a member", "add", "change group profile", "BACK"});
        this.user = user;
        this.group = group;
        this.members = group.getMembers();
        this.showMembers = new ShowEntities<>(members);
    }

    public void runMenu() {
        while (true) {
            showMembers.showEntities("no member");
            print();
            switch (chooseOperation()) {
                case 1:
                    showMembers.showPrevious();
                    break;
                case 2:
                    showMembers.showNext();
                    break;
                case 3:
                    User selectedMember = showMembers.select();
                    break;
                case 4:
                    String username = new Input("enter username: ").getInputString();
                    User member = ApplicationContext.getUserService().findByUsername(new SearchUserDto(username));
                    if (member != null) {
                        groupService.addMember(group, user, member);
                        showMembers.entityAdded();
                    }
                    break;
                case 5:
                    groupService.changeProfile(group, user);
                    break;
                case 6:
                    return;
            }
        }
    }
}
