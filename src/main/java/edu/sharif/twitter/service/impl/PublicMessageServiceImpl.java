package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.*;
import edu.sharif.twitter.repository.PublicMessageRepository;
import edu.sharif.twitter.service.PublicMessageService;

import java.util.List;

public abstract class PublicMessageServiceImpl<T extends PublicMessage> extends BaseEntityServiceImpl<T, Long , PublicMessageRepository<T>>
        implements PublicMessageService<T> {
    public PublicMessageServiceImpl(PublicMessageRepository<T> repository) {
        super(repository);
    }

    public abstract T createPublicMessage(User user, PublicMessage repliedTo);

    public abstract void editPublicMessage(T publicMessage);

    public abstract void showPublicMessage(User user);

    public abstract void deleteById(Long Id);

    public void showStats(PublicMessage publicMessage) {
        List<DateCount> viewDateCounts = repository.getViewCountPerDay(publicMessage);
        System.out.println("Views:");
        viewDateCounts.forEach(System.out::println);

        List<DateCount> likeDateCounts = repository.getLikeCountPerDay(publicMessage);
        System.out.println("Likes:");
        likeDateCounts.forEach(System.out::println);
    }
}
