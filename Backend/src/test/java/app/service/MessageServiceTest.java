package app.service;

import app.database.Message;
import app.service.database.MessageService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

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
        for(int i = 0; i < messageList.size(); i++)
        {
            if(messageList.get(i).getMsgContent().equals(message.getMsgContent()))
                found = true;
        }

        Assert.assertTrue(found);
    }
}
