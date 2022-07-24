package edu.sharif.twitter.entity;

import edu.sharif.twitter.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = UserProfile.TABLE_NAME)
@Getter
@Setter
public class UserProfile extends BaseEntity<Long> {

    public static final String TABLE_NAME = "user_profile";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String EMAIL = "email";
    public static final String AGE = "age";
    public static final String BIRTH_DAY = "birth_day";
    public static final String USER_ID = "user_id";

    @Column(name = FIRST_NAME, nullable = false)
    private String firstName;

    @Column(name = LAST_NAME, nullable = false)
    private String lastName;

    @Column(name = PHONE_NUMBER)
    private String phoneNumber;

    @Column(name = EMAIL)
    private String email;

    @Column(name = AGE)
    private Integer age;
    @Lob
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = USER_ID, nullable = false)
    private User user;

    @Override
    public String toString() {
        String dashLine = "----------";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);
        stringBuilder.append("\n");
        stringBuilder.append("First_name: ").append(firstName).append("\n");
        stringBuilder.append("Last_name: ").append(lastName).append("\n");
        stringBuilder.append("Phone_number: ").append(phoneNumber).append("\n");
        stringBuilder.append("email: ").append(email).append("\n");
        stringBuilder.append("age: ").append(age).append("\n");
        stringBuilder.append("bio: ").append(bio).append("\n");
        stringBuilder.append(dashLine).append(dashLine).append(dashLine);
        stringBuilder.append("\n\n");
        return stringBuilder.toString();
    }
}
