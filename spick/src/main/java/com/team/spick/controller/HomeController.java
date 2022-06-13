package com.team.spick.controller;

import com.team.spick.domain.Board;
import com.team.spick.dto.BoardRequestDto;
import com.team.spick.security.UserDetailsImpl;
import com.team.spick.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class HomeController {

    private final BoardService boardService;


    //게시글 생성
    @PostMapping("/api/input")
    public Board createBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.createBoard(boardRequestDto, userDetails);
    }

    //게시글 전체 조회
    @GetMapping("/api")
    public List<Board> homePage(){
        return boardService.homePage();
    }
}


