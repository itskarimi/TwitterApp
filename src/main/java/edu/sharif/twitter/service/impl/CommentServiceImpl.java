package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.CommentRepository;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.utils.input.Input;
import edu.sharif.twitter.utils.input.MyInput;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.time.LocalDateTime;

public class CommentServiceImpl extends PublicMessageServiceImpl<Comment>
        implements CommentService {

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public Comment createPublicMessage(User user, PublicMessage repliedTo, TextInputControl field) {
        Comment comment = new Comment();
        comment.setText(new MyInput(field).getInputTextString());
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setLastUpdateDateTime(LocalDateTime.now());
        comment.setRepliedTo(repliedTo);
        comment.setUser(user);
        comment.getUser().getComments().add(comment);
        return comment;
    }

    @Override
    public void editPublicMessage(Comment comment) {
        comment.setText(
                new Input("Enter your comment text :").getInputString()
        );
        comment.setLastUpdateDateTime(LocalDateTime.now());
        repository.getEntityManger().getTransaction().begin();
        repository.save(comment);
        repository.getEntityManger().getTransaction().commit();

    }

    @Override
    public void showPublicMessage(User user) {
        repository.showPublicMessage(user);
    }

    @Override
    public void deleteById(Long Id) {
        repository.deleteById(Id);
    }
}
