package com.serviceapplication;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.Random;

@Controller
public class WebSocketController {

    private static final String[] compliments = {
            "You look great today ",
            "I hope you have a good day ",
            "You're amazing ",
            "I believe in you "
    };

    private final Random random = new Random();

    @MessageMapping("/inbound")
    @SendTo("/outbound")
    public WebSocketSend compliment(WebSocketReceiver message) throws Exception {
        Thread.sleep(1000);
        return new WebSocketSend(compliments[random.nextInt(compliments.length - 1)] + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
