package com.example.demo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private Long userId;
    private String board_title;
    private String board_text;
    private String board_imgURL;
    private  String nickname;
    private  String user_picURL;
}
