package edu.sharif.twitter.utils.menu;

import java.util.List;

public class ShowUsersInformation<E> extends Menu{

    private final List<E> es;
    private boolean haveAllowedEdit;
    public ShowUsersInformation(List<String> texts , List<E> es) {
        super(texts);
        this.es = es;
    }

    public ShowUsersInformation(List<String> texts , List<E> es , boolean haveAllowEdit) {
        this(texts , es);
        this.haveAllowedEdit = haveAllowEdit;
    }

    public E runMenu() {
        while (true) {
            print();
            int chooseOption = chooseOperation();
            if (chooseOption == getItems().size())
                return null;
            else if (haveAllowedEdit){
                showTweet(chooseOption);
                return es.get(chooseOption -1);
            }
            else
                showTweet(chooseOption);

        }
    }

    public void showTweet(int chooseOption) {

        System.out.println(es.get(chooseOption - 1).toString());
    }
}
