package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.service.ChatService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.ShowEntities;
import edu.sharif.twitter.utils.input.Input;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowChatsMenu extends Menu {
    private User user;

    private List<Chat> chats;

    private ShowEntities<Chat> showChats;

    private ChatService chatService = ApplicationContext.getChatService();

    public ShowChatsMenu(User user) {
        super(Arrays.asList("show previous", "show next", "select a chat", "new group", "followings", "followers", "BACK"));
        this.user = user;
        chats = chatService.showChats(user);
        showChats = new ShowEntities<>(chats);
    }

    public void runMenu() throws IOException {
        while (true) {
            showChats.showEntities("no chat");
            print();
            switch (chooseOperation()) {
                case 1:
                    showChats.showPrevious();
                    break;
                case 2:
                    showChats.showNext();
                    break;
                case 3:
                    Chat selectedChat = showChats.select();
                    new ChatMenu(user, selectedChat).runMenu();
                    break;
                case 4:
                    String name = new Input("enter your group name: ").getInputString();
                    String description = new Input("enter your group description: ").getInputString();
                    List<User> members = new ArrayList<>();
                    members.add(user);
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
                    Group group = ApplicationContext.getGroupService().newGroup(user, name, description, members, null);
                    showChats.addEntity(group);
                    break;
                case 5:
                    //go to followings (for new DM)
                    break;
                case 6:
                    //go to followers (for new DM)
                    break;
                case 7:
                    return;
            }
        }
    }
}
