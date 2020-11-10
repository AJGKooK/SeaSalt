package app.controllers;

import app.database.Message;
import app.database.User;
import app.excpetions.NotFoundException;
import app.service.database.MessageService;
import app.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/messages")
public class MessageController {

    @Autowired
    SecurityService securityService;

    @Autowired
    MessageService messageService;

    @GetMapping(path = "/get")
    public Message getMessage(@RequestParam String username, @RequestParam String password, @RequestParam Integer id) {
        Optional<Message> message = messageService.getMessageById(id);
        if(message.isPresent()) {
            securityService.isAuthorizedHttp(username, password, message.get());
            return message.get();
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }

    @GetMapping(path = "/all")
    public ArrayList<Integer> getUserMessages(@RequestParam String username, @RequestParam String password) {
       User user = securityService.isAuthorizedHttp(username, password);
       ArrayList<Integer> messages = new ArrayList<>();
       for(Message message : user.getUserMessages()) {
           messages.add(message.getMsgId());
       }
       return messages;
    }

    @PostMapping(path = "/post")
    public Integer postMessage(@RequestParam String username, @RequestParam String password, @RequestParam String msgContent) {
        User user = securityService.isAuthorizedHttp(username, password);
        Message message = new Message(user, msgContent);
        messageService.saveMessage(message);
        return message.getMsgId();
    }

    @PostMapping(path = "/edit")
    public Integer editMessage(@RequestParam String username, @RequestParam String password, @RequestParam Integer id, @RequestParam String msgContent) {
        Optional<Message> message = messageService.getMessageById(id);
        if(message.isPresent()) {
            securityService.isAuthorizedHttp(username, password, message.get());
            message.get().setContent(msgContent);
            messageService.saveMessage(message.get());
            return message.get().getMsgId();
        } else {
            securityService.isAuthorizedHttp(username, password);
            throw new NotFoundException();
        }
    }
}
