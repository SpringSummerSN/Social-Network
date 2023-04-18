package spring.summer.socialnetwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.MessageDto;
import spring.summer.socialnetwork.models.Message;
import spring.summer.socialnetwork.repositories.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    public static final String MESSAGE_NOT_FOUND = "Message with id %s was not found.";
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MESSAGE_NOT_FOUND, id)));
    }

    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }

    public void addMessage(MessageDto messageDto) {
        Message message = mapToMessage(messageDto);
        messageRepository.save(message);
    }

    public void updateMessage(Long id, MessageDto messageDto) {
        messageRepository.findById(id).map(message -> {
            message.setSender(messageDto.getSender());
            message.setMessage(messageDto.getMessage());
            return messageRepository.save(message);
        }).orElseThrow(() -> new RuntimeException(String.format(MESSAGE_NOT_FOUND, id)));

    }

    private static Message mapToMessage(MessageDto messageDto) {
        return Message.builder()
                .sender(messageDto.getSender())
                .message(messageDto.getMessage())
                .build();
    }
}
