package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Chat;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.ChatRepository;
import edu.sharif.twitter.repository.DMRepository;
import edu.sharif.twitter.service.ChatService;
import edu.sharif.twitter.service.DMService;
import edu.sharif.twitter.utils.ApplicationContext;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DMServiceImpl extends BaseEntityServiceImpl<DM, Long, DMRepository>
            implements DMService {

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    private final ChatService chatService = ApplicationContext.getChatService();
    public DMServiceImpl(DMRepository repository) {
        super(repository);
    }

    @Override
    public DM newDM(User user1, User user2) {
        DM dm = findByUsers(user1, user2);
        if (dm != null)
            return dm;
        dm = new DM();
        dm.setCreateDateTime(LocalDateTime.now());
        dm.setMembers(Arrays.asList(user1, user2));
        dm.setIsDeleted(false);
        user1.getChats().add(dm);
        user2.getChats().add(dm);
        save(dm);
        return dm;
    }

    @Override
    public DM findByUsers(User user1, User user2) {
        List<DM> dms = repository.showDMs(user1);
        for (DM dm : dms)
            if (dm.getMembers().contains(user2))
                return dm;
        return null;
    }
}
