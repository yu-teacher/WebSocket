package com.chat.chat.staticElement;

import com.chat.chat.dto.RoomDTO;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StaticValue {

    //웹소켓 세션을 담아둘 리스트 ---roomListSessions
    public static List<HashMap<String, Object>> rls = Collections.synchronizedList(new ArrayList<>());

    // 서버에 있는 roomNumber and name List
    public static List<RoomDTO> roomList = Collections.synchronizedList(new ArrayList<>());

    // 전체 채팅방 유저 세션리스트
    public static List<WebSocketSession> userSessions = Collections.synchronizedList(new ArrayList<>());

    // 서버에 있는 전체 방 갯수
    public static int roomNumber = 0;
}
