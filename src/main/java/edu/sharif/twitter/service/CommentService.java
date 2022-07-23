package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.Comment;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;

public interface CommentService extends BaseEntityService<Comment, Long> {
//    List<String> commentsOfUser(Long id);

    void addComment(Tweet tweet , User user);

    void editComment(Comment comment);
}