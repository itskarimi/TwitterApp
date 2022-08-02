package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.Group;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.ShowEntities;

import java.util.Arrays;
import java.util.List;

public class ForwardMenu extends Menu{

    private User user;

    private Message message;

    private List<Chat> chats;

    private ShowEntities<Chat> showChats;

    public ForwardMenu(User user, Message message) {
        super(Arrays.asList("show previous", "show next", "select a chat", "cancel forward"));
        this.user = user;
        this.message = message;
        this.chats = user.getChats();
        this.showChats = new ShowEntities<>(chats);
    }

    public boolean runMenu() {
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
                    ApplicationContext.getMessageService().forwardMessage(user, selectedChat, message);
                    new ChatMenu(user, selectedChat).runMenu();
                    return true;
                case 4:
                    return false;
            }
        }
    }
}
