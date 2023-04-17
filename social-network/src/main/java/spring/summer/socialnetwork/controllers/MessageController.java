package spring.summer.socialnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.summer.socialnetwork.dto.MessageDto;
import spring.summer.socialnetwork.models.Message;
import spring.summer.socialnetwork.services.MessageService;

import java.util.List;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessageById(@PathVariable Long id) {
        messageService.deleteMessageById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addMessage(@RequestBody MessageDto messageDto) {
        messageService.addMessage(messageDto);
    }

    @PutMapping("/{id}")
    public void updateMessage(@PathVariable Long id, @RequestBody MessageDto messageDto) {
        messageService.updateMessage(id, messageDto);
    }

}
