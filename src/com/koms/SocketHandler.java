package com.koms;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.io.IOException;

/**
 * Created by leonid on 11.07.16.
 */
public class SocketHandler extends WebSocketHandler {

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(TestWebSocket.class);
    }

}
