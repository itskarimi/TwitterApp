package edu.sharif.twitter.repository;

import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.entity.DateCount;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.dto.SearchUserDto;

import java.util.List;

public interface UserRepository extends BaseEntityRepository<User, Long> {

    User existByUsername(String username);

    List<User> showTweetAllOfUsers();

    User findByUsername(SearchUserDto searchUserDto);

    List<DateCount> getViewCountPerDay(User user);
}
