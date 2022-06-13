package com.team.spick.controller;

import com.team.spick.domain.Board;
import com.team.spick.dto.BoardRequestDto;
import com.team.spick.repository.BoardRepository;
import com.team.spick.security.UserDetailsImpl;
import com.team.spick.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardDetailsController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    
    //게시글 상세조회
    @GetMapping("/api/detail/{board_id}")
    public Board detailsPage(@PathVariable Long board_id){
        Board board = boardRepository.findById(board_id).orElseThrow(()-> new IllegalArgumentException("존재하지않아 게시물이"));
        return board;
    }

    //게시글 삭제
    @DeleteMapping("/api/detail/{board_id}")
    public Long deletePage(@PathVariable Long board_id){
        boardRepository.deleteById(board_id);
        return board_id;
    }

    //게시글 수정
    @PutMapping("/api/detail/{board_id}")
    public Long fixPage(@PathVariable Long board_id, @RequestBody BoardRequestDto boardRequestDto){

        return boardService.fixPage(board_id, boardRequestDto);
    }
}
