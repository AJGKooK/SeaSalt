package app.service.database;

import app.database.MessageDatabase;
import app.database.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageDatabase messageDatabase;

    public List<Message> getAllMessages() {
        return messageDatabase.findAll();
    }

    public Optional<Message> getMessageById(Integer msgKey) {
        return messageDatabase.findById(msgKey);
    }

    public void saveMessage(Message message) {
        messageDatabase.save(message);
    }
}
