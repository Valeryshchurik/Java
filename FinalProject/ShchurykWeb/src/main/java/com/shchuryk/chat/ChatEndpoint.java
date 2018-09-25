package com.shchuryk.chat;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Egor Piankov
 */
@ServerEndpoint(value = "/chat", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {

    @OnMessage
    public void onMessage(final Session session, final ChatMessage chatMessage) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(chatMessage);
                }
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
}
