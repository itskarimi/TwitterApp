package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.utils.input.Input;

import java.util.List;

public class Menu {
    protected List<String> items;

    public Menu(List<String> items) {
        this.items = items;
    }

    public void print() {
        System.out.println("Menu options:");
        for (int i = 1; i <= items.size(); i++) {
            System.out.printf("%d - %s \n", i, items.get(i - 1));
        }
    }

    public int chooseOperation() {
        return new Input(
                "Enter your Item :",
                items.size() ,
                1 ,
                null).getInputInt();
    }

    public List<String> getItems() {
        return items;
    }
}
