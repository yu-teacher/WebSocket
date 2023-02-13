package com.chat.chat.controller;

import com.chat.chat.dto.RoomDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
public class WebSocketController {

    // 서버에 있는 전체 방 갯수
    private int roomNumber = 0;

    // 서버에 있는 roomNumber and name List
    private List<RoomDTO> roomList = new ArrayList<>();

    @RequestMapping("/chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chat");
        log.info("WebSocketController chat mv : "+mv);
        return mv;
    }

    /**
     * 방 페이지
     * @return
     */
    @RequestMapping("/room")
    public ModelAndView room() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("room");
        log.info("mv : "+mv);
        return mv;
    }

    /**
     * 방 생성하기
     * @param params
     * @return
     */
    @RequestMapping("/createRoom")
    public @ResponseBody List<RoomDTO> createRoom(@RequestParam HashMap<Object, Object> params){
        String roomName = (String) params.get("roomName");
        if(roomName != null && !roomName.trim().equals("")) {
            RoomDTO roomdto = new RoomDTO();
            roomdto.setRoomNumber(++roomNumber);
            roomdto.setRoomName(roomName);
            roomList.add(roomdto);
        }
        log.info("WebSocketController createRoom roomList : "+roomList);
        return roomList;
    }

    @RequestMapping("/deleteRoom")
    public @ResponseBody List<RoomDTO> deleteRoom(@RequestParam HashMap<Object, Object> params){
        log.info("WebSocketController deleteRoom params : "+params);
        String roomNumber = (String) params.get("roomNumber");
        String roomName = (String) params.get("roomName");
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomName(roomName);
        roomDTO.setRoomNumber(Integer.parseInt(roomNumber));
        log.info("WebSocketController deleteRoom roomDTO : "+roomDTO);
        roomList.remove(roomDTO);
        return roomList;
    }

    /**
     * 방 정보가져오기
     * @param params
     * @return
     */
    @RequestMapping("/getRoom")
    public @ResponseBody List<RoomDTO> getRoom(@RequestParam HashMap<Object, Object> params){
        log.info("WebSocketController getRoom roomList : "+roomList);
        return roomList;
    }

    /**
     * 채팅방
     * @return
     */
    @RequestMapping("/moveChatting")
    public ModelAndView chatting(@RequestParam HashMap<Object, Object> params) {
        ModelAndView mv = new ModelAndView();
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

        List<RoomDTO> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
        if(new_list != null && new_list.size() > 0) {
            mv.addObject("roomName", params.get("roomName"));
            mv.addObject("roomNumber", params.get("roomNumber"));
            mv.setViewName("chat");
        }else {
            mv.setViewName("room");
        }

        log.info("WebSocketController chatting mv : "+mv);
        return mv;
    }
}
