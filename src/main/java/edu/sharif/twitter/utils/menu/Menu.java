package edu.sharif.twitter.utils.menu;

import edu.sharif.twitter.utils.input.Input;

import java.util.List;

public class Menu {
    private String[] items;

    public Menu(String[] items) {
        this.items = items;
    }

    public void print() {
        System.out.println("Menu options:");
        for (int i = 1; i <= items.length; i++) {
            System.out.printf("%d - %s \n", i, items[i - 1]);
        }
    }

    public int chooseOperation() {
        return new Input(
                "Enter your Item :",
                items.length ,
                1 ,
                null).getInputInt();
    }

    public String[] getItems() {
        return items;
    }
}
