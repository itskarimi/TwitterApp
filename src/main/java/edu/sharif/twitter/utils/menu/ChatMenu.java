package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.service.ChatService;
import edu.sharif.twitter.service.DMService;
import edu.sharif.twitter.service.MessageService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.ShowEntities;
import edu.sharif.twitter.utils.input.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatMenu extends Menu {
    private User user;

    private Chat chat;

    private List<Message> messages;
    private final MessageService messageService = ApplicationContext.getMessageService();

    private ShowEntities<Message> showMessages;

    public ChatMenu(User user, Chat chat) {
        super(new String[] {"show profile", "show previous massages", "show next massages", "select a massage", "new massage", "BACK"});
        this.user = user;
        this.chat = chat;
        messages = chat.getMessages();
        showMessages = new ShowEntities<>(messages);
    }

    public ChatMenu(User user, User receiver) {
        this(user, ApplicationContext.getDmService().newDM(user, receiver));
    }

    public void runMenu() {
        while (true) {
            showMessages.showEntities("no chat history");
            print();
            switch (chooseOperation()) {
                case 1:
                    if (chat instanceof DM) {
                        if (chat.getMembers().get(0).equals(user))
                            new UserMenu(chat.getMembers().get(1)).runMenu();
                        else
                            new UserMenu(chat.getMembers().get(0)).runMenu();
                    }
                    else
                        new GroupMenu(user, (Group) chat).runMenu();
                    break;
                case 2:
                    showMessages.showPrevious();
                    break;
                case 3:
                    showMessages.showNext();
                    break;
                case 4:
                    Message selectedMessage = showMessages.select();
                    System.out.println(selectedMessage.getText() + " was selected");
                    int action = new SelectedMessageMenu(user, selectedMessage).runMenu();
                    if(action == 2) {
                        showMessages.removedEntity();
                    } else if (action == 3) {
                        Message reply = messageService.addReply(user, selectedMessage);
                        messages.add(reply);
                        showMessages.entityAdded();
                    } else if (action == 4) {
                        return;
                    }
                    break;
                case 5:
                    Message message = messageService.addMessage(user, chat);
                    messages.add(message);
                    showMessages.entityAdded();
                    break;
                case 6:
                    return;
            }
        }
    }
}