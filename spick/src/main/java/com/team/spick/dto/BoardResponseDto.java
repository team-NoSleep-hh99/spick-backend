package com.team.spick.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private Long userId;
    private String board_title;
    private String board_text;
    private String board_imgURL;
//    임시추가
    private String nickname;
    private String user_picURL;
}
