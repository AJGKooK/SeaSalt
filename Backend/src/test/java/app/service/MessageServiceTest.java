package app.service;

import app.database.entities.Message;
import app.service.database.MessageService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class MessageServiceTest {

    MessageService service;

    @Mock
    Message message;

    @Test
    public void messageSent()
    {
        service.saveMessage(message);

        List<Message> messageList = service.getAllMessages();

        boolean found = false;
        for (Message value : messageList) {
            if (value.getMsgContent().equals(message.getMsgContent())) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found);
    }
}
