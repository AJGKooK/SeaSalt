package app.websockets;

import app.excpetions.WebsocketException;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@ServerEndpoint("/chat/{username}")
@Component
public class Websocket {

    private final Map<Session, String> sessionToUsernameMap = new Hashtable<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessionToUsernameMap.put(session, username);
    }

    //It actually looks like we already have things set up for a separate system in MessageController.
    //How do we want to go about sending messages?
    //I'd suggest storing the message object in the database, but handling the IM part separately in websockets.
    @OnMessage
    public void onMessage(Session session, String message) {
        String username = sessionToUsernameMap.get(session);
        broadcast(username + ": " + message);
    }

    @OnClose
    public void onClose(Session session) {
        sessionToUsernameMap.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        sessionToUsernameMap.remove(session);
    }

    private void broadcast(String message) {
        sessionToUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new WebsocketException();
            }
        });
    }
}
