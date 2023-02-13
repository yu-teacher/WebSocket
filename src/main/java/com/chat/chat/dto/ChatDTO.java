package com.chat.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Builder
@ToString
@Getter
public class ChatDTO {

    private String type;
    private String userName;
    private String message;
    private LocalDateTime regDate;

}
