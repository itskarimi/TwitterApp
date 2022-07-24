package edu.sharif.twitter.repository;

import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.User;

import java.util.List;

public interface MessageRepository extends BaseEntityRepository<Message, Long> {

    void deleteById(Long id);

    List<Message> showMessages(DM dm);
}
