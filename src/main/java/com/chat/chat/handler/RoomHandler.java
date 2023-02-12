package com.chat.chat.handler;

import com.chat.chat.dto.RoomDTO;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

import static com.chat.chat.staticElement.Parser.jsonToObjectParser;
import static com.chat.chat.staticElement.StaticValue.*;

@Component
@Log4j2
public class RoomHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = message.getPayload();
        JSONObject obj = jsonToObjectParser(msg);
        log.info("RoomHandler handleTextMessage obj : "+obj);

        // 세션 리스트에 있는 모든 세션에 메시지 전달
        for (WebSocketSession s : userSessions) {
            s.sendMessage(new TextMessage(obj.toString()));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결
        userSessions.add(session);
        log.info("RoomHandler afterConnectionEstablished session : "+session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료
        if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
            for(int i = 0; i < rls.size(); i++) {
                rls.remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
    }

}
