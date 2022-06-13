package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.domain.Likes;
import com.example.demo.dto.DetailBoardResponseDto;
import com.example.demo.dto.BoardRequestDto;
import com.example.demo.dto.BoardResponseDto;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
//    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    // 게시글 전체 조회
    @GetMapping("/user/main")
    public List<Board> Boards() {

        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    // 카운트 베스트 5개 조회
    @GetMapping("/user/best")
    public List<Board> bestBoards() {
        List<Board> totalList = boardRepository.findAllByOrderByLikeCountDesc();
        List<Board> boardList = new ArrayList<>();
        for (int i =0; i<5; i++) {
            boardList.add(totalList.get(i));
        }
        return boardList;
    }



    // 게시글 상세 조회
    @GetMapping("/user/Board/{boardId}")
    public Board detailBoard(@PathVariable Long boardId) {
        Board board = new Board();
        board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );
        return board;
    }

    ///////////////////////////////////////////////////////////////

    //내가 작성한 게시글 조회
    @PostMapping("/api/Board/mypage")
    public List<DetailBoardResponseDto> myWriteBoard(@RequestBody BoardRequestDto boardRequestDto) {

        Long userId = boardRequestDto.getUserId();

        List<DetailBoardResponseDto> detailBoardResponseDto = boardService.mypageResponse(userId);

        return detailBoardResponseDto;
    }

    // 내가 관심을 누른 게시글 조회
    @PostMapping("/api/board/mylikepage")
    public List<DetailBoardResponseDto> myLikePost(@RequestBody BoardRequestDto boardRequestDto) {

        Long userId = boardRequestDto.getUserId();

        List<DetailBoardResponseDto> detailBoardResponseDto = boardService.mylikeResponse(userId);

        return detailBoardResponseDto;
    }


    // 게시글 상세 조회
    @PostMapping("/user/board/detail")
    public DetailBoardResponseDto detailPost(@RequestBody BoardResponseDto boardResponseDto) {

        System.out.println("게시글 상세조회 boardId " + boardResponseDto.getBoardId());
        System.out.println("게시글 상세조회 userId " + boardResponseDto.getUserId());
        DetailBoardResponseDto detailBoardResponseDto = boardService.detailBoard(boardResponseDto);

        return detailBoardResponseDto;
    }

    ///////////////////////////////////////////////////////////////////////////////


    // 게시글 작성
    @PostMapping("/api/board/write")
    public Board writeBoard(@RequestBody BoardRequestDto boardRequestDto) {
        System.out.println("게시글 생성 내용 : " + boardRequestDto.getBoard_text());
        System.out.println("게시글 생성 제목 : " + boardRequestDto.getBoard_title());
        System.out.println("게시글 생성 이미지url : " + boardRequestDto.getBoard_imgURL());
        System.out.println("게시글 생성 유저Id : " + boardRequestDto.getUserId());
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        return board;
    }

    // 게시글 작성 시 이미지 업로드
    @PutMapping("/api/board/write/image")
    public Long imagePost(@RequestBody BoardResponseDto BoardResponseDto) {
        System.out.println("게시글 이미지 업로드 이미지url : " + BoardResponseDto.getBoard_imgURL());
        System.out.println("게시글 이미지 업로드 boardId : " + BoardResponseDto.getBoardId());

        return boardService.Imageupdate(BoardResponseDto);

    }

    // 게시글 수정
    @PutMapping("/api/Board/write/{boardId}")
    public Long updatePost(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(boardId, boardRequestDto);
    }

    //게시글 삭제
    @DeleteMapping("/api/board/{boardId}")
    public Long deleteBoard(@PathVariable Long boardId) {

        boardRepository.deleteById(boardId);
        System.out.println("포스트 삭제 boardId = " + boardId );
//        List<Comments> commentsList = commentRepository.findAllByBoardId((boardId);
//        commentRepository.deleteAll(commentsList);
        System.out.println("댓글들 삭제완료 postId = " + boardId );

        List<Likes> likesList = likeRepository.findAllByBoardId((boardId));
        likeRepository.deleteAll(likesList);
        System.out.println("공감 객체들 삭제완료 boardId = " + boardId );

        return boardId;
    }



}


