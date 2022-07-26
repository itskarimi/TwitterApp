package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.DateCount;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public interface UserService extends BaseEntityService<User, Long> {
    User login(TextField usernameField, PasswordField passwordField, String css);

    User login();

    void signUp();

    boolean signUp(TextField usernameField, TextField passwordField, TextField confirmField, Image image, TextField passwordHintField,
                   TextField firstNameField, TextField lastNameField,
                   TextField emailField, TextField ageField, TextField bioField,
                   ToggleButton businessToggle, String css, Label warnings) throws IOException;

    User editUsername(User user);

    List<User> showTweetAllOfUsers();

    User findByUsername(SearchUserDto searchUserDto);

    void follow(User user, User following);

    String showProfile(User user);

    List<Tweet> showFollowingsTweets(User user);

    List<DateCount> showStats(User user);

    void setProfileImage(User user, Image image) throws IOException;

    Image getProfileImage(User user) throws IOException;

}
