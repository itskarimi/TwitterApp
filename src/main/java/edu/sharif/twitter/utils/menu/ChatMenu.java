package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.User;
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
        super(new String[] {"show previous massages", "show next massages", "select a massage", "new massage", "BACK"});
        this.user = user;
        this.chat = chat;
        messages = messageService.showMessages(chat);
        showMessages = new ShowEntities<>(messages);
    }

    public ChatMenu(User user, User receiver) {
        this(user, ApplicationContext.getDmService().newDM(user, receiver));
    }

    public void runMenu() {
        while (true) {
            System.out.println(messageService.showMessages(chat));
            //showMessages.showEntities("no chat history");
            print();
            switch (chooseOperation()) {
                case 1:
                    showMessages.showPrevious();
                    break;
                case 2:
                    showMessages.showNext();
                    break;
                case 3:
                    Message selectedMessage = showMessages.select();
                    System.out.println(selectedMessage.getText() + " was selected");
                    int action = new SelectedMessageMenu(user, selectedMessage).runMenu();
                    if(action == 2) {
                        showMessages.removeEntity(selectedMessage);
                    } else if (action == 3) {
                        Message reply = messageService.addReply(user, selectedMessage);
                        showMessages.addEntity(reply);
                        messageService.save(reply);
                    } else if (action == 4) {
                        return;
                    }
                    break;
                case 4:
                    Message message = messageService.addMessage(user, chat);
                    showMessages.addEntity(message);
                    messageService.save(message);
                    break;
                case 5:
                    return;
            }
        }
    }
}