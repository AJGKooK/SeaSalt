package com.serviceapplication;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public WebSocketSend compliment(WebSocketReceiver message) throws Exception {
        Thread.sleep(1000);
        return new WebSocketSend("You look great today " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
