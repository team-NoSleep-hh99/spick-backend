package com.team.spick.service;

import com.team.spick.domain.Board;
import com.team.spick.dto.BoardRequestDto;
import com.team.spick.repository.BoardRepository;
import com.team.spick.repository.UserRepository;
import com.team.spick.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //게시물 생성
    public Board createBoard(BoardRequestDto boardRequestDto, UserDetailsImpl userDetails){
        Board board = new Board(boardRequestDto, userDetails);

        return boardRepository.save(board);
    }

    //게시물 조회

    public List<Board> homePage(){
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    //상세게시글 수정
    @Transactional // update할때 DB에 반영되어야 한다고 한번 더 알려주는 어노테이션(업데이트 어노테이션에는 Transactional이 필수)
    public Long fixPage(Long board_id, BoardRequestDto boardRequestDto){
        Board board = boardRepository.findById(board_id).orElseThrow(()-> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        board.fixPage(boardRequestDto);

        return board.getBoard_id();
    }


}
