package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.repository.impl.BaseEntityRepositoryImpl;
import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.*;
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
    public Message addMessage(User user, Chat chat) {
        Message message = new Message();

        message.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());

        message.setCreateDateTime(LocalDateTime.now());
        message.setLastUpdateDateTime(LocalDateTime.now());
        message.setUser(user);
        message.setChat(chat);
        message.setIsForward(false);
        message.setIsReply(false);
        message.setIsDeleted(false);
        user.getMessages().add(message);
        transaction.begin();
        repository.save(message);
        transaction.commit();
        return message;
    }

    @Override
    public Message addReply(User user, Message message) {
        Message reply = new Message();

        reply.setText(new Input(
                "Enter your text :",
                "Your text must be a maximum of 280 characters",
                "", null).getInputTextString());

        reply.setCreateDateTime(LocalDateTime.now());
        reply.setLastUpdateDateTime(LocalDateTime.now());
        reply.setUser(user);
        reply.setChat(message.getChat());
        reply.setIsForward(false);
        reply.setIsReply(true);
        reply.setIsDeleted(false);
        reply.setOrigin(message);
        message.getReplies().add(reply);
        user.getMessages().add(reply);
        transaction.begin();
        repository.save(reply);
        transaction.commit();
        return reply;
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
    public List<Message> showMessages(Chat chat) {
        return repository.showMessages(chat);
    }

    @Override
    public void deleteById(Long id) {
        Message message = findById(id);
        this.delete(message);
    }

    @Override
    public void forwardMessage(User user, Chat chat, Message message) {
        Message forward = new Message();
        forward.setText(message.getText());
        forward.setCreateDateTime(LocalDateTime.now());
        forward.setLastUpdateDateTime(LocalDateTime.now());
        forward.setUser(user);
        forward.setChat(chat);
        forward.setIsForward(true);
        forward.setIsReply(false);
        forward.setIsDeleted(false);
        user.getMessages().add(forward);
        transaction.begin();
        repository.save(forward);
        transaction.commit();
    }

    public void delete(Message message) {
        for (Message message1 : message.getReplies()) {
            message1.setOrigin(null);
            message1.setIsReply(false);
        }
        super.delete(message);
    }
}
