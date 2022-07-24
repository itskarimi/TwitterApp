package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.DMService;
import edu.sharif.twitter.service.MessageService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DMMenu extends Menu {

    private final User user;
    private final DM dm;

    private int sortedNumber;

    private List<Message> messages;

    private final DMService dmService = ApplicationContext.getDmService();
    private final MessageService messageService = ApplicationContext.getMessageService();

    public DMMenu(User user, User receiver) {
        super(new String[] {"show previous massages", "show next massages", "select a massage", "new massage", "BACK"});
        if (!dmService.showDMs(user).contains(receiver)) {
            dmService.newDM(user, receiver);
        }
        this.user = user;
        this.dm = dmService.findByUsers(user, receiver);
        messages = messageService.showMessages(dm);
    }

    public void runMenu() {
        int newMessages = 0;
        int num;
        int to = messages.size();
        int from = Math.max(0, to - 5);
        sortedNumber = to - from;
        while (true) {
            showMessages(from, to);
            print();
            switch (chooseOperation()) {
                case 1:
                    num = Math.min(5, from);
                    if (num == 0) {
                        System.out.println("no previous message");
                        break;
                    }
                    System.out.println(num);
                    from -= num;
                    to -= num;
                    break;
                case 2:
                    num  = Math.min(5, messages.size() - to);
                    if (num <= 0) {
                        System.out.println("no next message");
                        break;
                    }
                    from += num;
                    to += num;
                    break;
                case 3:
                    break;
                case 4:
                    Message message = messageService.addMessage(user, dm, false);
                    messages.add(message);
                    sortedNumber++;
                    to = messages.size();
                    from = Math.max(0, to - 5);
                    break;
                case 5:
                    return;
            }
        }
    }

    public void showMessages(int from, int to) {
        int d = to - from;
        if (d == 0)
            System.out.println("no chat history");
        for (int i = d; i > 0; i--) {
            System.out.println(i + "- " + messages.get(to - i));
        }
        System.out.println(messages);
    }
}