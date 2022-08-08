package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.service.PublicMessageService;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;

import java.time.LocalDateTime;
import java.util.Arrays;

public class EditPublicMessageMenu<T extends PublicMessage> extends Menu{
    private T publicMessage;
    private final PublicMessageService<T> publicMessageService;
    private User user;

    public EditPublicMessageMenu(User user, T publicMessage, PublicMessageService<T> publicMessageService) {
        super(Arrays.asList("Edit Text" ,"Exit"));
        this.publicMessage = publicMessage;
        this.publicMessageService = publicMessageService;
        this.user = user;
    }

    public void runMenu() {
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    publicMessage.setText(new Input("Enter new text:").getInputString());
                    publicMessage.setLastUpdateDateTime(LocalDateTime.now());
                    publicMessageService.save(publicMessage);
                    break;
                case 2:
                    return;
            }
        }
    }
}
