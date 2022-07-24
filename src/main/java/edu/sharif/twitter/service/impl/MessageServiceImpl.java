package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.DM;
import edu.sharif.twitter.entity.Message;
import edu.sharif.twitter.entity.Tweet;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.repository.MessageRepository;
import edu.sharif.twitter.service.MessageService;
import edu.sharif.twitter.utils.input.Input;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

public class MessageServiceImpl extends BaseEntityServiceImpl<Message, Long, MessageRepository>
            implements MessageService {

    public MessageServiceImpl(MessageRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();


    @Override
    public Message addMessage(User user, DM dm, Boolean isReply) {
        Message message = new Message();

        message.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());

        message.setCreateDateTime(LocalDateTime.now());
        message.setLastUpdateDateTime(LocalDateTime.now());
        message.setUser(user);
        message.setDm(dm);
        message.setIsForward(false);
        message.setIsReply(isReply);
        message.setIsDeleted(false);
        user.getMessages().add(message);
        transaction.begin();
        repository.save(message);
        transaction.commit();
        return message;
    }

    @Override
    public void editMessage(Message message) {
        message.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());
        message.setLastUpdateDateTime(LocalDateTime.now());
        transaction.begin();
        repository.save(message);
        transaction.commit();
    }

    @Override
    public List<Message> showMessages(DM dm) {
        return repository.showMessages(dm);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
