package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.base.BaseEntity;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.utils.input.Input;

import java.util.List;

public class SelectMenu <E extends BaseEntity<Long>> extends Menu{

    public final List<E> entities;

    public SelectMenu(List<E> entities) {
        super(new String[] {"Select", "BACK"});
        this.entities = entities;
    }

    public void runMenu() {
        int i = 1;
        for(E e : entities){
            System.out.println(i + "- " + e);
            i++;
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
                        new UserMenu((User)entity).runMenu();
                    }
                    break;
                case 2:
                    return;
            }
        }
    }
}
