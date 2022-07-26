package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.PublicMessageRepository;
import edu.sharif.twitter.service.PublicMessageService;

public abstract class PublicMessageServiceImpl<T extends PublicMessage> extends BaseEntityServiceImpl<T, Long , PublicMessageRepository<T>>
        implements PublicMessageService<T> {
    public PublicMessageServiceImpl(PublicMessageRepository<T> repository) {
        super(repository);
    }

    public abstract T createPublicMessage(User user, PublicMessage repliedTo);

    public abstract void addPublicMessage(T publicMessage);

    public abstract void editPublicMessage(T publicMessage);

    public abstract void showPublicMessage(User user);

    public abstract void deleteById(Long Id);
}
