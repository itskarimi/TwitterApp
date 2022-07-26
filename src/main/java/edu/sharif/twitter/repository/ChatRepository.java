package edu.sharif.twitter.repository;

import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.User;

import java.util.List;

public interface ChatRepository extends BaseEntityRepository<Chat, Long> {

    List<Chat> showChats(User user);

    void deleteById(Long id);

}
