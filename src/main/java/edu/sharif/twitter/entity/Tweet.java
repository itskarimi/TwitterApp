package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import edu.sharif.twitter.service.impl.TweetServiceImpl;
import edu.sharif.twitter.utils.ApplicationContext;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tweet")
@DiscriminatorValue("tweet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tweet extends PublicMessage {

    @ManyToOne
    @JoinColumn(name = "tweet_user_id")
    protected User user;

    @Column(name = "tweet_image")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    protected byte[] image;

    @Override
    public String toString() {
        String dashLine = "----------";
        StringBuilder stringBuilder = new StringBuilder("@" + getUser().getUsername());
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);

        stringBuilder.append(text).append("\n\n");
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);
        stringBuilder.append("likes: ").append(likes.size()).append("\n");
        stringBuilder.append("comments: ").append(comments.size()).append("\n");
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);
        for (Comment comment : comments) {
            stringBuilder.append(comment.toString());
            stringBuilder.append("\n\n");
            stringBuilder.append(dashLine).append(dashLine).append(dashLine);

        }
        stringBuilder.append("\n\n");
        return stringBuilder.toString();
    }

    public void setImage(Image img) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img, null);
        bufferedImage = TweetServiceImpl.resize(bufferedImage, 200, 147);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.image = byteArrayOutputStream.toByteArray();
    }
}
