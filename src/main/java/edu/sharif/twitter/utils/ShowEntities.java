package edu.sharif.twitter.utils;

import edu.sharif.twitter.base.BaseEntity;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.utils.input.Input;

import java.util.List;

public class ShowEntities<E extends BaseEntity<Long>>  {

    private int from, to, sortedNumber;

    private List<E> entities;

    public ShowEntities(List<E> entities) {
        this.entities = entities;
        to = entities.size();
        from = Math.max(0, to - 5);
        sortedNumber = to - from;
    }

    public void showEntities(String emptyWarning) {
        int d = to - from;
        if (d == 0)
            System.out.println(emptyWarning);
        for (int i = d; i > 0; i--) {
            System.out.println(i + "- " + entities.get(to - i));
        }
    }
    public void showPrevious() {
        int num = Math.min(5, from);
        if (num == 0) {
            System.out.println("no previous");
            return;
        }
        System.out.println(num);
        from -= num;
        to -= num;
    }
    public void showNext() {
        int num  = Math.min(5, entities.size() - to);
        if (num <= 0) {
            System.out.println("no next");
            return;
        }
        from += num;
        to += num;
    }

    public void addEntity(E entity) {
        entities.add(entity);
        sortedNumber++;
        to = entities.size();
        from = Math.max(0, to - 5);
    }

    public void removeEntity(E entity) {
        sortedNumber--;
        entities.remove(entity);
        if (from == 0 && to != entities.size())
            to++;
        else if (from > 0) {
            from--;
            to--;
        }
    }

    public E select() {
        int number = new Input("select a number",
                (long) (to - from),
                1L).getInputInt();
        E selected = entities.get(to - number);
        return selected;
    }

    public void setEntities(List<E> entities) {
        this.entities = entities;
    }
}
