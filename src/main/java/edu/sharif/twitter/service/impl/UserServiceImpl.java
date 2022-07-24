package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;
import edu.sharif.twitter.repository.UserRepository;
import edu.sharif.twitter.service.UserService;
import edu.sharif.twitter.utils.InputInformation;
import edu.sharif.twitter.utils.input.Input;

import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl extends BaseEntityServiceImpl<User, Long, UserRepository>
    implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User login() {

        String username = new Input("Enter your username :").getInputString();
        String password = new Input("Enter your password :").getInputString();
        User user = repository.existByUsername(username);
        try{
            if (user.getPassword().equals(password)){
                return user;
            }
        } catch (NullPointerException ignored){

        }
        return null;
    }
    @Override
    public void signUp() {
        User user = new User();

        user.getUserProfile().setFirstName(InputInformation.getFirstName());

        user.getUserProfile().setLastName(InputInformation.getLastName());

        String username = new Input("Enter your username").getInputString();

        while (repository.existByUsername(username) != null) {
            System.out.println("this username is token before");
            username = new Input("Enter your username :").getInputString();
        }

        user.setUsername(username);
        user.setIsDeleted(false);

        String password;
        while(true) {
            password = new Input("Enter your password").getInputString();
            if (password.length() < 8) {
                System.out.println("your password isn't long enough");
            } else if (password.matches("[^a-z]*")) {
                System.out.println("your password should have lowercase letter");
            } else if (password.matches("[^A-Z]*")) {
                System.out.println("your password should hove uppercase letter");
            } else if (password.matches("\\D*")) {
                System.out.println("your password should have numbers");
            } else {
                break;
            }
        }
        while(true) {
            String password1 = new Input("Reenter your password").getInputString();
            if (password1.equals(password))
                break;
            System.out.println("your passwords don't match");
        }

        user.setPassword(password);

        user.getUserProfile().setPhoneNumber(InputInformation.getPhoneNumber());

        user.getUserProfile().setEmail(new Input("Enter your email :").getInputString());

        user.getUserProfile().setAge(new Input("Enter your Age :").getInputInt());

        user.getUserProfile().setBio(new Input("Enter your bio : ").getInputString());


        user.getUserProfile().setUser(user);

        repository.getEntityManger().getTransaction().begin();
        repository.save(user);
        repository.getEntityManger().getTransaction().commit();

        System.out.println("You are signup successfully...");

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
    public void follow(User user) {
        String username = new Input("Enter username :").getInputTextString();
        User following = findByUsername(new SearchUserDto(username));
        if (following == null) {
            System.out.println("no such user found");
            return;
        }
        if (user.getFollowings().contains(following)) {
            System.out.printf("%s is already among your followings\n", username);
            return;
        }
        user.getFollowings().add(following);
        System.out.printf("%s was successfully followed\n", username);
    }

    @Override
    public void unfollow(User user) {
        String username = new Input("Enter username :").getInputTextString();
        User following = findByUsername(new SearchUserDto(username));
        if (following == null) {
            System.out.println("no such user found");
            return;
        }
        if (!user.getFollowings().contains(following)) {
            System.out.printf("%s isn't among your followings\n", username);
            return;
        }
        user.getFollowings().remove(following);
        System.out.printf("%s was successfully unfollowed\n", username);
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
}
