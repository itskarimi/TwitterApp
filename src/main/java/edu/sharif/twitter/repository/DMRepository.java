package edu.sharif.twitter.repository;

import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.User;

import java.util.List;

public interface DMRepository extends BaseEntityRepository<DM, Long> {

    List<DM> showDMs(User user);

}
