package edu.sharif.twitter.utils.menu;

public class CheckMenu extends Menu {
    public CheckMenu() {
        super(new String[]{"YES", "NO"});
    }


    public boolean runMenu() {
        print();
        return chooseOperation() == 1;
    }
}
