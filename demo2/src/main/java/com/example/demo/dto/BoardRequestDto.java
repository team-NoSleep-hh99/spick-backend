package com.example.demo.dto;

import lombok.*;

@Getter
@AllArgsConstructor // 필드값을 모두 포함한 생성자를 자동 생성.
@NoArgsConstructor // 기본생성자 만듬
public class BoardRequestDto {

    private  Long userId;
    private  String board_title;
    private  String board_text;
    private  String board_imgURL;
    private  String nickname;
    private  String user_picURL;
}
