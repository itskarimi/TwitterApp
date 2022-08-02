package edu.sharif.twitter.repository;

import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;

public interface LikeRepository extends BaseEntityRepository<Like, Long> {
    Like findByUserAndPublicMessage(User user, PublicMessage publicMessage);
}
