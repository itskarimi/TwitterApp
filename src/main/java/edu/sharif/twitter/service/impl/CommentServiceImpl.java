package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.CommentRepository;
import edu.sharif.twitter.service.CommentService;
import edu.sharif.twitter.utils.input.Input;

import java.time.LocalDateTime;

public class CommentServiceImpl extends BaseEntityServiceImpl<Comment, Long , CommentRepository>
        implements CommentService {

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
/*
    @Override
    public List<String> commentsOfUser(Long id) {
        return repository.commentsOfUser(id);
    }*/

    @Override
    public void addComment(Tweet tweet , User user) {
        Comment comment = new Comment();
        comment.setTextComment(new Input("Enter your comment :").getInputString());
        comment.setCreateDateTime(LocalDateTime.now());
        comment.setLastUpdateDateTime(LocalDateTime.now());
        comment.setTweet(tweet);
        comment.setUser(user);
        user.getComments().add(comment);

        repository.getEntityManger().getTransaction().begin();
        repository.save(comment);
        repository.getEntityManger().getTransaction().commit();
    }

    @Override
    public void editComment(Comment comment) {
        comment.setTextComment(
                new Input("Enter your comment text :").getInputString()
        );
        comment.setLastUpdateDateTime(LocalDateTime.now());
        repository.getEntityManger().getTransaction().begin();
        repository.save(comment);
        repository.getEntityManger().getTransaction().commit();

    }
}
