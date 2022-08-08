package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.repository.TweetRepository;
import edu.sharif.twitter.service.TweetService;
import edu.sharif.twitter.utils.ApplicationContext;
import edu.sharif.twitter.utils.input.Input;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.persistence.EntityTransaction;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TweetServiceImpl extends PublicMessageServiceImpl<Tweet>
        implements TweetService {

    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    @Override
    public Tweet createPublicMessage(User user, PublicMessage repliedTo, String text) {
        Tweet tweet = new Tweet();

        tweet.setText(text);

        tweet.setCreateDateTime(LocalDateTime.now());
        tweet.setLastUpdateDateTime(LocalDateTime.now());
        tweet.setUser(user);
        tweet.getUser().getTweets().add(tweet);

        return tweet;
    }

    @Override
    public void editPublicMessage(Tweet tweet) {
        tweet.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());
        tweet.setLastUpdateDateTime(LocalDateTime.now());
        transaction.begin();
        repository.save(tweet);
        transaction.commit();
    }

    @Override
    public void showPublicMessage(User user) {
        repository.showPublicMessage(user);
    }

    @Override
    public void deleteById(Long Id) {
        repository.deleteById(Id);
    }

    @Override
    public List<Comment> getComments(Tweet publicMessage) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment comment : publicMessage.getComments()) {
            comments.addAll(ApplicationContext.getCommentService().getComments(comment));
        }
        System.out.println(comments.size());
        ArrayList<Comment> sorted = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            int s = 0;
            for (int j = 0; j < comments.size(); j++)
                if (comments.get(j) != null && (comments.get(s) == null ||
                        comments.get(j).getCreateDateTime().isBefore(comments.get(s).getCreateDateTime())))
                    s = j;
            System.out.println(i + "\t" + comments.get(s));
            sorted.add(comments.get(s));
            comments.set(s, null);
        }
        return sorted;
    }


    @Override
    public List<DateCount> getViewStat(PublicMessage publicMessage) {
        return repository.getViewCountPerDay(publicMessage);
    }

    @Override
    public List<DateCount> getLikeStat(PublicMessage publicMessage) {
        return repository.getLikeCountPerDay(publicMessage);
    }

    @Override
    public void delete(Tweet tweet) {
        tweet.getUser().getTweets().remove(tweet);
        transaction.begin();
        repository.delete(tweet);
        transaction.commit();
    }

    @Override
    public void setImage(Tweet tweet, Image image) {
        tweet.setImage(image);
        this.save(tweet);
    }

    @Override
    public void deleteImage(Tweet tweet) {
        Image image = getImage(tweet);
        if (image == null)
            return;
        tweet.setImage(null);
    }

    @Override
    public Image getImage(Tweet tweet) {
        if (tweet.getImage() == null)
            return null;

        byte[] byteArray = tweet.getImage();

        ByteArrayInputStream inStream = new ByteArrayInputStream(byteArray);

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int h, w;
        if ((double) newH/newW > (double) img.getHeight()/img.getWidth()) {
            h = (int) ((double) img.getHeight()/img.getWidth()*newW);
            w = newW;
        } else {
            w = (int) ((double) img.getWidth()/img.getHeight()*newH);
            h = newH;
        }
        java.awt.Image tmp = img.getScaledInstance(h, w, java.awt.Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
