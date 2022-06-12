package com.example.demo.domain;
import com.example.demo.dto.BoardRequestDto;
import com.example.demo.dto.BoardResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@NoArgsConstructor // 기본 생성자 만듬.
@Getter // 조회를 하기 위해 있어야 됨.
@Entity // 테이블과 연계됨을 스프링에게 알려줌=
public class Board extends Timestamped { // 생성 , 수정 시간을 자동으로 만듬.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long boardId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private  String board_title;

    @Column(nullable = false)
    private  String board_text;

    @Column
    private  String board_imgURL;

    @Column
    private Long likeCount;

//    @OneToMany
//    @JoinColumn(name = "COMMENTS_ID")
//    private List<Comments> commentList;

    public Board(BoardRequestDto boardRequestDto)  {
        this.userId = boardRequestDto.getUserId();
        this.board_title = boardRequestDto.getBoard_title();
        this.board_text = boardRequestDto.getBoard_text();
        this.board_imgURL = boardRequestDto.getBoard_imgURL();
        this.likeCount = 0L;

    }
    public void update(BoardRequestDto boardRequestDto) {
        this.board_title = boardRequestDto.getBoard_title();
        this.board_text = boardRequestDto.getBoard_text();
        this.board_imgURL = boardRequestDto.getBoard_imgURL();
    }

    //이미지 업데이트 할때 필요
    public void imageUpdate(BoardResponseDto boardResponseDto) {
        this.board_imgURL = boardResponseDto.getBoard_imgURL();
    }
    //좋아요카운트할때 필요
    public void likeCount(Long Count) {
        this.likeCount = Count;
    }


}