package com.team.spick.domain;
import com.team.spick.dto.BoardRequestDto;
import com.team.spick.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본 생성자 만듬.
@Getter // 조회를 하기 위해 있어야 됨.
@Entity // 테이블과 연계됨을 스프링에게 알려줌=
public class Board extends Timestamped { // 생성 , 수정 시간을 자동으로 만듬.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column(nullable = false)
    private String board_title;

    @Column(nullable = false)
    private String board_text;

    @Column(nullable = false)
    private String board_imgURL;

    @Column(nullable = false)
    private String nickname;


    public Board(BoardRequestDto boardRequestDto, UserDetailsImpl userDetails) {
        this.board_title = boardRequestDto.getBoard_title();
        this.board_text = boardRequestDto.getBoard_text();
        this.board_imgURL = boardRequestDto.getBoard_imgURL();
        this.nickname = userDetails.getUser().getNickname();
    }

    public void fixPage(BoardRequestDto boardRequestDto) {
        this.board_title = boardRequestDto.getBoard_title();
        this.board_text = boardRequestDto.getBoard_text();
        this.board_imgURL = boardRequestDto.getBoard_imgURL();
    }
}