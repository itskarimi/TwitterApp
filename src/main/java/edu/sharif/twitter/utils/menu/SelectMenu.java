package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.base.BaseEntity;
import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.service.ViewService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;

import java.util.Arrays;
import java.util.List;

public class SelectMenu <E extends BaseEntity<Long>> extends Menu{

    private final User user;
    private final List<E> entities;
    private final ViewService viewService = ApplicationContext.getViewService();

    public SelectMenu(User user, List<E> entities) {
        super(Arrays.asList("Select", "BACK"));
        this.entities = entities;
        this.user = user;
    }

    public void runMenu() {
        if (entities.isEmpty()){
            System.out.println("Nothing was found");
            return;
        }
        int i = 1;
        for(E e : entities){
            System.out.println(i + "- " + e);
            i++;
        }
        if (entities.get(0) instanceof PublicMessage) {
            for (E e : entities) {
                viewService.addView(user, (PublicMessage) e);
            }
        }
        while(true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    int n = new Input(
                            "Enter the number :",
                            entities.size() ,
                            1 ,
                            null).getInputInt();
                    E entity = entities.get(n-1);
                    if(entity instanceof User) {
                        new UserMenu(user, (User)entity).runMenu();
                    }
                    if(entity instanceof Tweet) {
                        new PublicMessageMenu<>(user, (Tweet)entity, Tweet.class).runMenu();
                    }
                    if(entity instanceof Comment) {
                        new PublicMessageMenu<>(user, (Comment)entity, Comment.class).runMenu();
                    }
                    break;
                case 2:
                    return;
            }
        }
    }
}
