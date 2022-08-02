package edu.sharif.twitter.utils.menu;

import java.util.Arrays;

public class CheckMenu extends Menu {
    public CheckMenu() {
        super(Arrays.asList("YES", "NO"));
    }


    public boolean runMenu() {
        print();
        return chooseOperation() == 1;
    }
}
