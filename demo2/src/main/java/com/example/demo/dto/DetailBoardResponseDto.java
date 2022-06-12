package com.example.demo.dto;

import com.example.demo.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetailBoardResponseDto {
    private Long boardId;
    private Long userId;
    private String board_title;
    private String board_text;
    private String board_imgURL;
    private LikeDto likeDto;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;

    public DetailBoardResponseDto(Board board, LikeDto likeDto) {
        this.boardId = board.getBoardId();
        this.board_title = board.getBoard_title();
        this.board_text = board.getBoard_text();
        this.board_imgURL = board.getBoard_imgURL();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt =board.getModifiedAt();
        this.userId = board.getUserId();
        this.likeDto = likeDto;

    }

    public DetailBoardResponseDto(Board board, Long likeCount) {
        this.boardId = board.getBoardId();
        this.board_title = board.getBoard_title();
        this.board_text = board.getBoard_text();
        this.board_imgURL =board.getBoard_imgURL();
        this.createdAt = board.getCreatedAt();
        this.likeCount = likeCount;
    }





}
