package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
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

@Entity
@Table(name = "group_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupProfile extends BaseEntity<Long> {
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profileImage;

    @Column(name = "name")
    private String name;

    @Column(name = "desrcription")
    private String description;

    @OneToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    public void setProfileImage(Image image) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        this.profileImage = bytes;
    }
}
