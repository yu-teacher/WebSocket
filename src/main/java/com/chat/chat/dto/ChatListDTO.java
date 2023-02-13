package com.chat.chat.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatListDTO {

    private static ChatListDTO getInstance = null;
    private HashMap<Integer,ArrayList<ChatDTO>> chatListMap = new HashMap<>();
    private ChatListDTO(){}
    public void chatMapLog(int roomNumber, ChatDTO chatDTO){
        if (chatListMap.containsKey(roomNumber)){
            ArrayList<ChatDTO> list = chatListMap.get(roomNumber);
            list.add(chatDTO);
            chatListMap.put(roomNumber, list);
        }else {
            ArrayList<ChatDTO> list = new ArrayList<>();
            list.add(chatDTO);
            chatListMap.put(roomNumber, list);
        }
    }

    public ArrayList<ChatDTO> getLog(int roomNumber){
        if(chatListMap.containsKey(roomNumber)){
            return chatListMap.get(roomNumber);
        }else {
            return new ArrayList<>();
        }
    }

    public static ChatListDTO getInstance() {
        if (getInstance == null) {
            getInstance = new ChatListDTO();
        }
        return getInstance;
    }

}
