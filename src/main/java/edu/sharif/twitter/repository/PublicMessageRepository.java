package edu.sharif.twitter.repository;

import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.entity.DateCount;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;

import java.util.Date;
import java.util.List;

public interface PublicMessageRepository<T extends PublicMessage> extends BaseEntityRepository<T, Long> {
    void showPublicMessage(User user);

    void deleteById(Long id);

    List<DateCount> getViewCountPerDay(PublicMessage publicMessage);

    List<DateCount> getLikeCountPerDay(PublicMessage publicMessage);
}
