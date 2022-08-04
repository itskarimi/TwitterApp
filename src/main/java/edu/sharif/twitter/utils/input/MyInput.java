package edu.sharif.twitter.utils.input;

import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyInput {

    private String inputString;
    private TextInputControl field;

    public MyInput(TextInputControl field) {
        this.field = field;
    }

    public String getInputTextString() {
        inputString = field.getText();
        return inputString;
    }

}
