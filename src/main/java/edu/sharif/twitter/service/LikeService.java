package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;

public interface LikeService extends BaseEntityService<Like, Long> {

    Like addLike(User user, PublicMessage publicMessage);

    Like findByUserAndPublicMessage(User user, PublicMessage publicMessage);
}
