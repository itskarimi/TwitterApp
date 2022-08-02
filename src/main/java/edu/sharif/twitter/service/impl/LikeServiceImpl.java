package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.LikeRepository;
import edu.sharif.twitter.service.LikeService;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class LikeServiceImpl extends BaseEntityServiceImpl<Like, Long, LikeRepository>
        implements LikeService {
    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    public Like addLike(User user, PublicMessage publicMessage) {
        Like like = new Like();
        like.setUser(user);
        like.setPublicMessage(publicMessage);
        like.setCreateDateTime(LocalDateTime.now());
        like.getPublicMessage().getLikes().add(like);
        like.getUser().getLikes().add(like);

        transaction.begin();
        repository.save(like);
        transaction.commit();

        return like;
    }

    public Like findByUserAndPublicMessage(User user, PublicMessage publicMessage) {
        return repository.findByUserAndPublicMessage(user, publicMessage);
    }

    @Override
    public void delete(Like like) {
        like.getUser().getLikes().remove(like);
        like.getPublicMessage().getLikes().remove(like);
        transaction.begin();
        repository.delete(like);
        transaction.commit();
    }
}
