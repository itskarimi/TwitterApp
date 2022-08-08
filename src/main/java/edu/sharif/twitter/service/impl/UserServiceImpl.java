package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.DateCount;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.repository.UserRepository;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.InputInformation;
import edu.sharif.twitter.utils.input.Input;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl extends BaseEntityServiceImpl<User, Long, UserRepository>
    implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User login(TextField usernameField, PasswordField passwordField,
                      String css) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = repository.existByUsername(username);
        try{
            if (user.getPassword().equals(password)){
                usernameField.getStylesheets().remove(css);
                passwordField.getStylesheets().remove(css);
                return user;
            }
            else {
                usernameField.getStylesheets().add(css);
                passwordField.getStylesheets().add(css);
            }
        } catch (NullPointerException ignored){
            usernameField.getStylesheets().add(css);
            passwordField.getStylesheets().add(css);
        }
        return null;
    }
    @Override
    public boolean signUp(TextField usernameField, TextField passwordField, TextField confirmField, Image image, TextField passwordHintField,
                          TextField firstNameField, TextField lastNameField,
                          TextField emailField, TextField ageField, TextField bioField,
                          ToggleButton businessToggle, String css, Label warnings) throws IOException {

        boolean done = true;

        User user = new User();

        String errors = "";

        String firstName = firstNameField.getText();
        if (!firstName.matches(InputInformation.FIRSTNAME_REGEX)) {
            firstNameField.getStylesheets().add(css);
            done = false;
        }
        else {
            user.getUserProfile().setFirstName(firstName);
            firstNameField.getStylesheets().remove(css);
        }

        String lastName = lastNameField.getText();
        if (!firstName.matches(InputInformation.LASTNAME_REGEX)) {
            lastNameField.getStylesheets().add(css);
            done = false;
        }
        else {
            user.getUserProfile().setLastName(lastName);
            lastNameField.getStylesheets().remove(css);
        }

        String username = usernameField.getText();
        if (repository.existByUsername(username) != null) {
            usernameField.getStylesheets().add(css);
            done = false;
        }
        else {
            user.setUsername(username);
            user.setIsDeleted(false);
            usernameField.getStylesheets().remove(css);
        }

        String password = passwordField.getText(), confirm = confirmField.getText();
        passwordField.getStylesheets().add(css);
        confirmField.getStylesheets().add(css);
        if (password.length() < 8) {
            errors += "your password isn't long enough";
            done = false;
        } else if (password.matches("[^a-z]*")) {
            errors += "your password should have lowercase letter";
            done = false;
        } else if (password.matches("[^A-Z]*")) {
            errors += "your password should hove uppercase letter";
            done = false;
        } else if (password.matches("\\D*")) {
            errors += "your password should have numbers";
            done = false;
        } else if (!confirm.equals(password)) {
            errors += "your passwords don't match";
            done = false;
        } else {
            user.setPassword(password);
            passwordField.getStylesheets().remove(css);
            confirmField.getStylesheets().remove(css);
        }


        String email = emailField.getText();
        if (email != null) {
            user.getUserProfile().setEmail(email);
            emailField.getStylesheets().remove(css);
        }
        else
            emailField.getStylesheets().add(css);

        String age = ageField.getText();
        if (age != null) {
            user.getUserProfile().setAge(Integer.parseInt(age));
            ageField.getStylesheets().remove(css);
        }
        else
            ageField.getStylesheets().add(css);

        String bio = bioField.getText();
        if (bio != null) {
            user.getUserProfile().setBio(bio);
            bioField.getStylesheets().remove(css);
        }
        else
            bioField.getStylesheets().add(css);

        String passwordHint = passwordHintField.getText();
        if (passwordHint != null) {
            user.getUserProfile().setPasswordHint(passwordHint);
            passwordHintField.getStylesheets().remove(css);
        }
        else
            passwordHintField.getStylesheets().add(css);

        user.setIsBusiness(businessToggle.isSelected());

        user.setProfileImage(image);

        user.getUserProfile().setUser(user);

        warnings.setText(errors);
        warnings.setLayoutX(280);
        warnings.setLayoutY(490);

        if (done) {
            repository.getEntityManger().getTransaction().begin();
            repository.save(user);
            repository.getEntityManger().getTransaction().commit();
        }
        return done;
    }


    @Override
    public User editUsername(User user) {
        String username = new Input("Enter your username").getInputString();

        while (repository.existByUsername(username) != null) {
            System.out.println("this username is token before");
            username = new Input("Enter your username :").getInputString();
        }

        user.setUsername(username);
        return user;
    }

    @Override
    public List<User> showTweetAllOfUsers() {
        return repository.showTweetAllOfUsers();
    }

    @Override
    public User findByUsername(SearchUserDto searchUserDto) {
        return repository.findByUsername(searchUserDto);
    }

    @Override
    public void follow(User user, User following) {
        String username = following.getUsername();
        if (user.getFollowings().contains(following)) {
            user.getFollowings().remove(following);
            following.getFollowers().remove(user);
            System.out.printf("%s was successfully unfollowed\n", username);
        }
        else {
            user.getFollowings().add(following);
            following.getFollowers().add(user);
            System.out.printf("%s was successfully followed\n", username);
        }

        repository.getEntityManger().getTransaction().begin();
        repository.save(user);
        repository.getEntityManger().getTransaction().commit();
    }

    @Override
    public String showProfile(User user) {
        StringBuilder profile = new StringBuilder(user.getUsername()).append("\n");
        profile.append(user.getUserProfile().getFirstName() + " " + user.getUserProfile().getLastName()).append("\n");
        profile.append("tweets: " + user.getTweets().size()).append("\n");
        profile.append("followers: " + user.getFollowers().size()).append("\n");
        profile.append("followings: " + user.getFollowings().size()).append("\n");
        return profile.toString();
    }

    @Override
    public List<Tweet> showFollowingsTweets(User user) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (User following : user.getFollowings()) {
            tweets.addAll(following.getTweets());
        }
        return tweets;
    }

    public void showStats(User user) {
        List<DateCount> dateCounts = repository.getViewCountPerDay(user);
        dateCounts.forEach(System.out::println);
    }

    @Override
    public Image getProfileImage(User user) throws IOException {
        byte[] byteArray = user.getProfileImage();

        ByteArrayInputStream inStream = new ByteArrayInputStream(byteArray);

        BufferedImage bufferedImage = ImageIO.read(inStream);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    @Override
    public void setProfileImage(User user, Image image) throws IOException {

        user.setProfileImage(image);
        this.save(user);
    }
}
