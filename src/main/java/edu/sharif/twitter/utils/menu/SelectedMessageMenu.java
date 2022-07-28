package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.MessageService;
import edu.sharif.twitter.utils.ApplicationContext;
import org.hibernate.type.ListType;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class SelectedMessageMenu extends Menu {

    private final User user;
    private Message message;

    private final MessageService messageService = ApplicationContext.getMessageService();

    public SelectedMessageMenu(User user, Message message) {
        super(new String[] {"edit", "delete", "reply", "forward", "BACK"});
        this.message = message;
        this.user = user;
    }

    int runMenu() {
        //returns 0: no action 1:edit 2:delete 3:reply 4:forward
        while(true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    LocalDateTime now = LocalDateTime.now();
                    if (Duration.between(message.getCreateDateTime(), now).toHours() > 12) {
                        System.out.println("you can't edit this message anymore");
                        return 0;
                    }
                    if (!message.getUser().equals(user)) {
                        System.out.println("you can only edit your own messages");
                        return 0;
                    }
                    else if (message.getIsForward()) {
                        System.out.println("you can't edit forwarded message");
                        return 0;
                    }
                    messageService.editMessage(message);
                    return 1;
                case 2:
                    messageService.deleteById(message.getId());
                    return 2;
                case 3:
                    return 3;
                case 4:
                    boolean forwarded = new ForwardMenu(user, message).runMenu();
                    if (forwarded)
                        return 4;
                    return 0;
                case 5:
                    return 0;
            }
        }
    }
}
