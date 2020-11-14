package app;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;

@ServerEndpoint("/websocket/{username}")
@Component
public class Websocket {

    private Map<Session, String> sessionToUsernameMap;
    private Map<String, Session> usernameToSessionMap;

    @OnOpen
    public void onOpen (Session session, @PathParam("username") String username)
    {
        sessionToUsernameMap.put(session, username);
        usernameToSessionMap.put(username, session);
    }

    //It actually looks like we already have things set up for a separate system in MessageController.
    //How do we want to go about sending messages?
    @OnMessage
    public void onMessage(String username, String message)
    {

    }

    @OnClose
    public void onClose(Session session)
    {
        String username = sessionToUsernameMap.get(session);
        sessionToUsernameMap.remove(session);
        usernameToSessionMap.remove(username);
    }

    private void sendToClient()
    {

    }

    private void broadcast()
    {

    }
}
